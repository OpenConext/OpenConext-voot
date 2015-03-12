package voot;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voot.oauth.SchacHomeAuthentication;
import voot.valueobject.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    LOG.debug("me/groups on behalf of uid {}, schacHomeOrg: {} ", authentication.getName(), schacHome);

    final List<Group> myGroups = externalGroupsService.getMyGroups(authentication.getName(), schacHome);

    LOG.debug("me/groups result for uid {}: {}", authentication.getName(), myGroups);
    return myGroups;

  }

  @RequestMapping(value = "/groups/{groupId}")
  public Group specificGroup(@PathVariable String groupId, final OAuth2Authentication authentication) {
    String schacHome = ((SchacHomeAuthentication) authentication.getUserAuthentication()).getSchacHomeAuthentication();
    LOG.debug("groups/{} on behalf of uid {}, schacHomeOrg: {} ", groupId, authentication.getName(), schacHome);

    final Optional<Group> group = externalGroupsService.getMyGroupById(authentication.getName(), groupId, schacHome);

    LOG.debug("groups/{} result for uid {}: {}", groupId, authentication.getName(), group);

    if (group.isPresent()) {
      return group.get();
    } else {
      throw new ResourceNotFoundException();
    }
  }

}
