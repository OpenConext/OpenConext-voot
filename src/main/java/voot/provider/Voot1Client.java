package voot.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Voot1Client extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  public Voot1Client(Configuration configuration) {
    super(configuration);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return configuration.schacHomeOrganization.equals(schacHomeOrganization);
  }

  @Override
  public boolean shouldBeQueriedForGroup(String schacHomeOrganization, String groupId) {
    return configuration.schacHomeOrganization.equals(schacHomeOrganization);
  }

  @Override
  public List<Group> getGroupMemberships(String uid) {
    LOG.debug("Querying getGroupMemberships for subjectId: {} and configuration: {}", uid, configuration);

    uid = stripPersonUrnIdentifier(uid);

    ResponseEntity<Map> response = restTemplate.getForEntity(String.format("%s/groups/{uid}", configuration.url), Map.class, uid);

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseGroups((List<Map<String, Object>>) response.getBody().get("entry"));
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Collections.emptyList();
    }

  }

  @Override
  public Optional<Group> getGroupMembership(String uid, String groupId) {
    LOG.debug("Querying getGroupMembership for subjectId: {} and configuration: {}", uid, configuration);

    uid = stripPersonUrnIdentifier(uid);
    groupId = stripGroupUrnIdentifier(groupId);

    ResponseEntity<Map> response = restTemplate.getForEntity(String.format("%s/groups/{uid}/{groupId}", configuration.url), Map.class, uid, groupId);

    if (response.getStatusCode().is2xxSuccessful()) {
      List<Group> groups = parseGroups((List<Map<String, Object>>) response.getBody().get("entry"));
      return groups.isEmpty() ? Optional.empty() : Optional.of(groups.get(0));
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Optional.empty();
    }
  }

  private List<Group> parseGroups(List<Map<String, Object>> groups) {
    return groups.stream().map(m -> parseGroup(m)).collect(Collectors.toList());
  }

  private Group parseGroup(Map<String, Object> map) {
    Object idHolder = map.get("id");
    String  id = idPrefix + (String) (idHolder instanceof Map ? ((Map) idHolder).get("groupId") : idHolder);
    return new Group(id, (String) map.get("title"), (String) map.get("description"), Membership.fromRole((String) map.getOrDefault("voot_membership_role", "member")));
  }


}
