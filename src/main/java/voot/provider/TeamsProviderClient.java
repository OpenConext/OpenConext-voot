package voot.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import voot.util.UrnUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static java.util.Collections.emptyList;

public class TeamsProviderClient extends Voot2Provider implements TeamsProvider {

  public TeamsProviderClient(Configuration configuration) {
    super(configuration);
  }

  @Override
  public boolean isTeamsGroup(String groupId) {
    Matcher matcher = UrnUtils.GROUP_PATTERN.matcher(groupId);
    return matcher.matches() && matcher.group(1).equals(configuration.schacHomeOrganization);
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public Optional<Group> findByLocalGroupId(String localGroupId) {
    URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/group/%s", configuration.url, localGroupId))
      .build().encode().toUri();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return handleResponse(response, this::parseSingleGroup, "findByLocalGroupId", Optional.empty());
  }

  @Override
  public List<Group> linkedLocalTeamsGroup(Collection<String> fullyQualifiedExternalGroupIds) {
    URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/linked-locals",configuration.url))
      .queryParam("external-group-ids", String.join(",", fullyQualifiedExternalGroupIds))
      .build().encode().toUri();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return handleResponse(response, this::parseGroups, "findByLocalGroupId", emptyList());
  }

  @Override
  public List<String> linkedExternalGroupIds(String... teamIds) {
    URI uri = UriComponentsBuilder.fromHttpUrl(String.format(configuration.url, "%s/linked-externals"))
      .queryParam("team-ids", String.join(",", teamIds))
      .build().encode().toUri();
    ResponseEntity<List> response = restTemplate.getForEntity(uri, List.class);
    return handleResponse(response, s -> s, "findByLocalGroupId", emptyList());
  }

  @Override
  public List<Member> getMembers(String personId, String groupId) {
    return getMembers(groupId);
  }

  @Override
  public List<Member> getMembers(String groupId) {
    throw new IllegalArgumentException("Voot2Providers do not support getting members");
  }

  @Override
  public List<Group> getAllGroups() {
    return emptyList();
  }

}
