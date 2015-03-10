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
  public List<Map<String, Object>> myGroups(final OAuth2Authentication authentication) {
    String schacHome = ((SchacHomeAuthentication) authentication.getUserAuthentication()).getSchacHomeAuthentication();
    LOG.debug("On behalf of uid {}, schacHomeOrg: {} ", authentication.getName(), schacHome);
    final List<Group> myGroups = externalGroupsService.getMyGroups(authentication.getName(), schacHome);

    LOG.debug("Found groups: {}", myGroups);

    return myGroups.stream()
      .map(group -> ImmutableMap.<String, Object>of(
        "id", group.id,
        "displayName", group.displayName))
      .collect(Collectors.toList());

  }

  @RequestMapping(value = "/groups")
  public List<Map<String, Object>> allGroups() {
    return ImmutableList.of(
      ImmutableMap.of(
        "id", "1234-5678",
        "displayName", "UNINETT Systemavdelingen"),
      ImmutableMap.of(
        "id", "1234-1234",
        "displayName", "UNINETT Tjenesteavdelingen")
    );
  }


  @RequestMapping(value = "/groups/{groupId}")
  public Map<String, Object> specificGroup(@PathVariable final String groupId) {
    return ImmutableMap.of("id", groupId, "displayName", "foo");
  }

}
