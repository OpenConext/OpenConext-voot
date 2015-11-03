package voot;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;
import voot.util.UrnUtils;
import voot.valueobject.Group;

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

    LOG.debug("me/groups on behalf of uid: {}, schacHomeOrg: {}, accessToken: {}, clientId: {}", authentication.getName(), schacHome, accessToken, clientId);

    final List<Group> myGroups = externalGroupsService.getMyGroups(authentication.getName(), schacHome);

    LOG.debug("me/groups result for uid {}: {}", authentication.getName(), myGroups);
    return myGroups;

  }

  @RequestMapping(value = "/me/groups/{groupId:.+}")
  public Group specificGroupMembership(@PathVariable final String groupId, final OAuth2Authentication authentication) throws MalformedGroupUrnException{
    String schacHome = ((SchacHomeAuthentication) authentication.getUserAuthentication()).getSchacHomeAuthentication();
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.debug("groups/{} on behalf of uid {}, schacHomeOrg: {}, accessToken: {}, clientId {}", groupId, authentication.getName(), schacHome, accessToken, clientId);
    if (! UrnUtils.isFullyQualifiedGroupName(groupId)) {
      throw new MalformedGroupUrnException(groupId);
    }
    final Optional<Group> group = externalGroupsService.getMyGroupById(authentication.getName(), groupId);

    LOG.debug("groups/{} result for uid {}: {}", groupId, authentication.getName(), group);

    return group.orElseThrow(ResourceNotFoundException::new);
  }

  @RequestMapping(value = "/internal/groups/{userId:.+}/{groupId:.+}")
  public Group internalSpecificGroup(@PathVariable final String userId, @PathVariable final String groupId, final OAuth2Authentication authentication) throws MalformedGroupUrnException {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.debug("internal/groups/{}/{}, accessToken: {}, clientId {}", userId, groupId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    Optional<String> schacHome = UrnUtils.getSchacHomeFromGroupUrn(groupId);
    if (!schacHome.isPresent()) {
      throw new MalformedGroupUrnException(groupId);
    }
    final Optional<Group> group = externalGroupsService.getMyGroupById(userId, groupId);

    LOG.debug("groups/{} result: {}", groupId, group);

    return group.orElseThrow(ResourceNotFoundException::new);
  }

  @RequestMapping(value = "/internal/groups/{userId:.+}")
  public List<Group> internalGroups(@PathVariable String userId, final OAuth2Authentication authentication) throws MalformedPersonUrnException {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.debug("internal/groups/{}, accessToken: {}, clientId {}", userId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    Optional<String> schacHome = UrnUtils.getSchacHomeFromPersonUrn(userId);
    if (!schacHome.isPresent()) {
      throw new MalformedPersonUrnException(userId);
    }
    final List<Group> myGroups = externalGroupsService.getMyGroups(userId, schacHome.get());

    LOG.debug("internal/groups/{} result: {}", userId, myGroups);

    return myGroups;
  }

  @RequestMapping(value = "/internal/external-groups/{userId:.+}")
  public List<Group> externalGroups(@PathVariable String userId, final OAuth2Authentication authentication) throws MalformedPersonUrnException {
    String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    String clientId = authentication.getOAuth2Request().getClientId();

    LOG.debug("internal/external-groups/{}, accessToken: {}, clientId {}", userId, accessToken, clientId);

    assertClientCredentialsClient(authentication, clientId);

    Optional<String> schacHome = UrnUtils.getSchacHomeFromPersonUrn(userId);
    if (!schacHome.isPresent()) {
      throw new MalformedPersonUrnException(userId);
    }
    final List<Group> groups = externalGroupsService.getMyExternalGroups(userId, schacHome.get());

    LOG.debug("internal/external-groups/{} result: {}", userId, groups);

    return groups;
  }

  private void assertClientCredentialsClient(OAuth2Authentication authentication, String clientId) {
    if (!(authentication.getUserAuthentication() instanceof ClientCredentialsAuthentication)) {
      throw new AccessDeniedException(String.format("ClientCredentials grant type required. ClientId is %s", clientId));
    }
  }

  @ExceptionHandler(MalformedUrnException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ModelMap handleMalformedPersonUrnException(MalformedUrnException exception) {
    ModelMap model = new ModelMap();
    model.put("error", exception.getMessage());
    return model;
  }

  public static abstract class MalformedUrnException extends Exception {
    public MalformedUrnException(String message) {
      super(message);
    }
  }

  public static class MalformedPersonUrnException extends MalformedUrnException {
    private static final String MESSAGE_FORMAT = "%s is not a valid person-urn. Values must adhere to regexp: %s";

    public MalformedPersonUrnException(String incorrectValue) {
      super(String.format(MESSAGE_FORMAT, incorrectValue, UrnUtils.URN_COLLAB_PERSON_REGEXP));
    }
  }

  public static class MalformedGroupUrnException extends MalformedUrnException {
    private static final String MESSAGE_FORMAT = "%s is not a valid group-urn. Values must adhere to regexp: %s";

    public MalformedGroupUrnException(String incorrectValue) {
      super(String.format(MESSAGE_FORMAT, incorrectValue, UrnUtils.URN_COLLAB_GROUP_REGEXP));
    }
  }
}
