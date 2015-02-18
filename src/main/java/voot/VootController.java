package voot;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@RestController
public class VootController {

  private static Logger LOG = LoggerFactory.getLogger(VootController.class);

  @RequestMapping(value = "/me/groups")
  public List<Map<String, Object>> myGroups(final Principal principal) {
    LOG.debug("User on whose behalf this request is made is {} ", principal.getName());
    Map<String, Object> first = ImmutableMap.of(
      "id", "8878ae43-965a-412a-87b5-38c398a76569",
      "displayName", "Some group this user (" + principal.getName() + ") belongs to.",
      "notBefore", LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );
    return ImmutableList.of(first);

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
