package voot.provider;

import org.springframework.http.ResponseEntity;
import voot.util.UrnUtils;
import voot.valueobject.Member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@SuppressWarnings("unchecked")
public class OpenSocialMembersClient extends OpenSocialClient {

  private final String membersTemplate;

  public OpenSocialMembersClient(Configuration configuration) {
    super(configuration);
    membersTemplate = "%s/people/{uid}/{groupId}";
  }

  @Override
  public boolean shouldBeQueriedForMembers(String groupId) {
    return true;
  }

  @Override
  public List<Member> getMembers(String personId, String groupId) {
    LOG.debug("Querying getMembers for personId: {} and groupId: {}", personId, groupId);

    String localGroupId = UrnUtils.extractLocalGroupId(groupId);
    String localPersonId = UrnUtils.extractLocalUid(personId);
    String url = String.format(membersTemplate, configuration.url);
    ResponseEntity<String> response = restTemplate.getForEntity(url,
      String.class, localPersonId, localGroupId);

    return handleResponse(response, this::parseMembers, "getMembers", emptyList());
  }

  private List<Member> parseMembers(String body) {
    List<Map<String, Object>> entries = (List<Map<String, Object>>) ((Map) parseJson(body, Map.class).get("result")).get("entry");
    return entries.stream().map(map -> {
      String id = (String) map.get("id");
      String name = ((Map<String, String>) map.get("name")).get("formatted");
      List<String> emails = (List<String>) map.get("emails");
      return new Member(id, name, emails != null && !emails.isEmpty() ? emails.get(0) : null);
    }).collect(Collectors.toList());
  }


}
