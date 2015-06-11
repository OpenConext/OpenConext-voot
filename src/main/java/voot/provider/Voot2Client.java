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
import voot.valueobject.Membership;

public class Voot2Client extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GrouperSoapClient.class);

  public Voot2Client(Configuration configuration) {
    super(configuration);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return configuration.schacHomeOrganization.equals(schacHomeOrganization);
  }

  @Override
  public boolean shouldBeQueriedForGroup(String schacHomeOrganization, String groupId) {
    return shouldBeQueriedForMemberships(schacHomeOrganization);
  }

  @Override
  public List<Group> getGroupMemberships(final String uid) {
    LOG.debug("Querying getGroupMemberships for subjectId: {} and name: {}", uid, configuration.schacHomeOrganization);

    final Optional<String> localUid = UrnUtils.extractLocalUid(uid);
    if (!localUid.isPresent()) {
      throw new IllegalArgumentException("Unable to extract local uid from " + uid);
    }
    ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/groups/{uid}", configuration.url), String.class, localUid.get());

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseGroups(response.getBody());
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Collections.emptyList();
    }

  }

  @Override
  public Optional<Group> getGroupMembership(final String uid, final String groupId) {
    LOG.debug("Querying getGroupMembership for subjectId: {} and name: {}", uid, configuration.schacHomeOrganization);

    final Optional<String> localUid = UrnUtils.extractLocalUid(uid);
    if (!localUid.isPresent()) {
      throw new IllegalArgumentException("Unable to extract local uid from: " + uid);
    }

    final Optional<String> localGroupId = UrnUtils.extractLocalGroupId(groupId);
    if (!localGroupId.isPresent()) {
      throw new IllegalArgumentException("Unable to extract local group id from:" + groupId);
    }


    ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/groups/{uid}/{groupId}", configuration.url), String.class, localUid.get(), localGroupId.get());

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseSingleGroup(response.getBody());
    } else {
      LOG.error("Failed to invoke getGroupMemberships {} for {}, returning empty result.", response, configuration);
      return Optional.empty();
    }
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
      item.containsKey("membership") ? Membership.fromRole((String) ((Map) item.get("membership")).get("basic")) : Membership.defaultMembership);
  }


}
