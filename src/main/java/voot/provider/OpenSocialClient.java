package voot.provider;

import org.springframework.util.CollectionUtils;
import voot.model.Group;
import voot.model.Membership;

import java.util.*;
import java.util.stream.Collectors;

public class OpenSocialClient extends Voot2Provider {

    public OpenSocialClient(Configuration configuration) {
        super(configuration);
        allMembershipsUrlTemplate = "%s/groups/{uid}";
        specificMembershipTemplate = "%s/groups/{uid}/{groupId}";
    }

    @Override
    protected Set<Group> parseGroups(String response) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> groups = (List) parseJson(response, Map.class).get("entry");
        if (CollectionUtils.isEmpty(groups)) {
            return Collections.emptySet();
        }
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
        }).collect(Collectors.toSet());
    }

    @Override
    protected Optional<Group> parseSingleGroup(String response) {
        Set<Group> groups = parseGroups(response);
        return groups.isEmpty() ? Optional.empty() : Optional.of(groups.iterator().next());
    }

}
