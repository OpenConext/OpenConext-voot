package voot.provider;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import voot.util.UrnUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

public class Voot2Provider extends AbstractProvider {

  protected static final Logger LOG = LoggerFactory.getLogger(Voot2Provider.class);
  protected String allMembershipsUrlTemplate;
  protected String specificMembershipTemplate;

  public Voot2Provider(Configuration configuration) {
    super(configuration);
    allMembershipsUrlTemplate = "%s/user/{uid}/groups";
    specificMembershipTemplate = "%s/user/{uid}/groups/{groupId}";
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return configuration.schacHomeOrganization.equalsIgnoreCase(schacHomeOrganization);
  }

  @Override
  public List<Group> getGroupMemberships(final String uid) {
    LOG.debug("Querying getGroupMemberships for subjectId: {} and name: {}", uid, configuration.schacHomeOrganization);

    String localUid = UrnUtils.extractLocalUid(uid);
    ResponseEntity<String> response = restTemplate.getForEntity(String.format(allMembershipsUrlTemplate, configuration.url), String.class, localUid);

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseGroups(response.getBody());
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Collections.emptyList();
    }
  }

  @Override
  public List<Group> getAllGroups() {
    return Collections.emptyList();
  }

  @Override
  public Optional<Group> getGroupMembership(final String uid, final String groupId) {
    LOG.debug("Querying getGroupMembership for subjectId: {} and name: {}", uid, configuration.schacHomeOrganization);

    String localUid = UrnUtils.extractLocalUid(uid);
    String localGroupId = UrnUtils.extractLocalGroupId(groupId);

    final String url = String.format(specificMembershipTemplate, configuration.url);
    LOG.debug("Invoking {} on provider {}", url, this);
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, localUid, localGroupId);

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseSingleGroup(response.getBody());
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Optional.empty();
    }
  }

  @Override
  public List<Member> getMembers(String groupId) {
    throw new IllegalArgumentException("Voot2Providers do not support getting members");
  }

  @Override
  public List<Member> getMembers(String personId, String groupId) {
    return getMembers(groupId);
  }

  @SuppressWarnings("unchecked")
  protected List<Group> parseGroups(String response) {
    List<Map<String, Object>> maps = parseJson(response, List.class);
    return maps.stream().map(this::parseGroup).collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  protected Optional<Group> parseSingleGroup(String response) {
    return Optional.of(parseGroup(parseJson(response, Map.class)));
  }

  private Group parseGroup(Map<String, Object> item) {
    return new Group(
      groupIdPrefix + item.get("id"),
      (String) item.get("displayName"),
      (String) item.get("description"),
      configuration.name,
      item.containsKey("membership") ? new Membership((String) ((Map) item.get("membership")).get("basic")) : Membership.MEMBER);
  }


}
