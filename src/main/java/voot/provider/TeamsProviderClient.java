package voot.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import voot.util.UrnUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

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
      .queryParam("externalGroupIds", String.join(",", fullyQualifiedExternalGroupIds))
      .build().encode().toUri();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return handleResponse(response, this::parseGroups, "findByLocalGroupId", emptyList());
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> linkedExternalGroupIds(String localGroupId) {
    URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/linked-externals", configuration.url))
      .queryParam("teamId", localGroupId)
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
    String uri = String.format( "%s/members/{groupId}",configuration.url);
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, groupId);
    return handleResponse(response, this::parseMembers, "getMembers", emptyList());
  }

  @Override
  public List<Group> getAllGroups() {
    String uri = String.format("%s/groups", configuration.url);
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return handleResponse(response, this::parseGroups, "getAllGroups", emptyList());
  }

  private List<Member> parseMembers(String response) {
    List<Map<String, String>> maps = parseJson(response, List.class);
    return maps.stream().map(map -> new Member(map.get("id"),map.get("name"),map.get("email"))).collect(toList());
  }

}
