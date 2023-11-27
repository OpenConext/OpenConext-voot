package voot.provider;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import voot.model.Group;
import voot.model.Member;
import voot.model.Membership;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class EduIDGuestProvider extends AbstractProvider {

    private final String groupMembershipsUrlTemplate;

    public EduIDGuestProvider(Configuration configuration) {
        super(configuration);
        groupMembershipsUrlTemplate = "%s/api/voot/%s";
    }

    @Override
    public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
        return true;
    }

    @Override
    public Set<Group> getGroupMemberships(String uid) {
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, URI.create(String.format(groupMembershipsUrlTemplate, configuration.url, uid)));
        ResponseEntity<List<Map<String, String>>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Map<String, String>>>() {
        });
        return responseEntity.getBody().stream().map(this::parseGroup).collect(Collectors.toSet());
    }

    private Group parseGroup(Map<String, String> map) {
        String name = map.get("name");
        return new Group(map.get("urn"), name, name, "eduID", Membership.MEMBER);
    }

    @Override
    public Set<Group> getAllGroups() {
        return Collections.emptySet();
    }

    @Override
    public Optional<Group> getGroupMembership(String uid, String groupId) {
        return Optional.empty();
    }

    @Override
    public Set<Member> getMembers(String groupId) {
        return Collections.emptySet();
    }

    @Override
    public Set<Member> getMembers(String personId, String groupId) {
        return Collections.emptySet();
    }
}
