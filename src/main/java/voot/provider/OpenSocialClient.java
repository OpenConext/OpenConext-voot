package voot.provider;

import voot.valueobject.Group;
import voot.valueobject.Membership;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenSocialClient extends Voot2Provider {

  public OpenSocialClient(Configuration configuration) {
    super(configuration);
    allMembershipsUrlTemplate = "%s/groups/{uid}";
    specificMembershipTemplate = "%s/groups/{uid}/{groupId}";
  }

  @Override
  protected List<Group> parseGroups(String response) {
    @SuppressWarnings("unchecked")
    List<Map<String, Object>> groups = (List) parseJson(response, Map.class).get("entry");
    return groups.stream().map(map -> {
      Object idHolder = map.get("id");
      //deprecated Open Social protocol has compound ID
      String id = groupIdPrefix + (idHolder instanceof Map ? ((Map) idHolder).get("groupId") : idHolder);
      return new Group(
        id,
        (String) map.get("title"),
        (String) map.get("description"),
        configuration.name,
        new Membership((String) map.getOrDefault("voot_membership_role", "member")));
    }).collect(Collectors.toList());
  }

  @Override
  protected Optional<Group> parseSingleGroup(String response) {
    List<Group> groups = parseGroups(response);
    return groups.isEmpty() ? Optional.empty() : Optional.of(groups.get(0));
  }

}
