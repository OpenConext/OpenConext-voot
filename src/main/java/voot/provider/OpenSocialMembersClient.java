package voot.provider;

import org.springframework.http.ResponseEntity;
import voot.util.UrnUtils;
import voot.valueobject.Member;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    Optional<String> localGroupId = UrnUtils.extractLocalGroupId(groupId);
    if (!localGroupId.isPresent()) {
      throw new IllegalArgumentException("Unable to extract local groupId from " + groupId);
    }
    String url = String.format(membersTemplate, configuration.url);
    ResponseEntity<String> response = restTemplate.getForEntity(url,
      String.class, personId, localGroupId.get());

    if (response.getStatusCode().is2xxSuccessful()) {
      return parseMembers(response.getBody());
    } else {
      LOG.error("Failed to invoke getMembers {} for {}, returning empty result.", response, configuration);
      return Collections.emptyList();
    }
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
