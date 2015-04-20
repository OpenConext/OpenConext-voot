package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;
import voot.provider.AbstractProvider;
import voot.valueobject.Group;

import java.util.*;

@RestController
public class VootController {

  private static Logger LOG = LoggerFactory.getLogger(VootController.class);

  private ExternalGroupsService externalGroupsService;

  @Autowired
  public VootController(ExternalGroupsService externalGroupsService) {
    this.externalGroupsService = externalGroupsService;
  }

  @RequestMapping(value = "/me/groups")
  public List<Group> myGroups(final OAuth2Authentication authentication) {
    String schacHome = ((SchacHomeAuthentication) authentication.getUserAuthentication()).getSchacHomeAuthentication();
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.info("me/groups on behalf of uid: {}, schacHomeOrg: {}, accessToken: {}, clientId: {}", authentication.getName(), schacHome, accessToken, clientId);

    final List<Group> myGroups = externalGroupsService.getMyGroups(authentication.getName(), schacHome);

    LOG.debug("me/groups result for uid {}: {}", authentication.getName(), myGroups);
    return myGroups;

  }

  @RequestMapping(value = "/me/groups/{groupId:.+}")
  public Group specificGroupMembership(@PathVariable String groupId, final OAuth2Authentication authentication) {
    String schacHome = ((SchacHomeAuthentication) authentication.getUserAuthentication()).getSchacHomeAuthentication();
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.info("groups/{} on behalf of uid {}, schacHomeOrg: {}, accessToken: {}, clientId {}", groupId, authentication.getName(), schacHome, accessToken, clientId);

    final Optional<Group> group = externalGroupsService.getMyGroupById(authentication.getName(), groupId, schacHome);

    LOG.debug("groups/{} result for uid {}: {}", groupId, authentication.getName(), group);

    return group.orElseThrow(ResourceNotFoundException::new);
  }

  @RequestMapping(value = "/internal/groups/{userId:.+}/{groupId:.+}")
  public Group internalSpecificGroup(@PathVariable String userId, @PathVariable String groupId, final OAuth2Authentication authentication) {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.info("internal/groups/{}/{}, accessToken: {}, clientId {}", userId, groupId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    String schacHome = AbstractProvider.getSchacHomeFromGroupUrn(groupId);
    final Optional<Group> group = externalGroupsService.getMyGroupById(userId, groupId, schacHome);

    LOG.debug("groups/{} result: {}", groupId, group);

    return group.orElseThrow(ResourceNotFoundException::new);
  }

  @RequestMapping(value = "/internal/groups/{userId:.+}")
  public List<Group> internalGroups(@PathVariable String userId, final OAuth2Authentication authentication) {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.info("internal/groups/{}, accessToken: {}, clientId {}", userId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    String schacHome = AbstractProvider.getSchacHomeFromPersonUrn(userId);
    final List<Group> myGroups = externalGroupsService.getMyGroups(userId, schacHome);

    LOG.debug("groups/{} result: {}", userId, myGroups);

    return myGroups;
  }

  @RequestMapping(value = "/internal/external-groups/{userId:.+}")
  public List<Group> externalGroups(@PathVariable String userId, final OAuth2Authentication authentication) {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.info("internal/external-groups/{}, accessToken: {}, clientId {}", userId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    String schacHome = AbstractProvider.getSchacHomeFromPersonUrn(userId);
    final List<Group> groups = externalGroupsService.getMyExternalGroups(userId, schacHome);

    LOG.debug("internal/external-groups/{} result: {}", userId, groups);

    return groups;
  }

  private void assertClientCredentialsClient(OAuth2Authentication authentication, String clientId) {
    if (!(authentication.getUserAuthentication() instanceof ClientCredentialsAuthentication)) {
      throw new AccessDeniedException(String.format("ClientCredentials grant type required. ClientId is %s", clientId));
    }
  }


}
