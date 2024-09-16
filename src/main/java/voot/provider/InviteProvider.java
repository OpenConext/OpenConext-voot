package voot.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import voot.model.Group;
import voot.model.Member;
import voot.model.Membership;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class InviteProvider extends AbstractProvider {

    private static final Logger LOG = LoggerFactory.getLogger(InviteProvider.class);

    private final String groupMembershipsUrlTemplate;

    public InviteProvider(Configuration configuration) {
        super(configuration);
        groupMembershipsUrlTemplate = String.format("%s", configuration.url.endsWith("/") ? configuration.url : (configuration.url + "/"));
    }

    @Override
    public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
        return true;
    }

    @Override
    public boolean isExternalGroupProvider() {
        return false;
    }


    @Override
    public Set<Group> getGroupMemberships(String uid) {
        LOG.debug("Calling getGroupMemberships for " + uid);

        String uri = groupMembershipsUrlTemplate + uid;
        RequestEntity<List<Map<String, String>>> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(uri));
        return restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Map<String, String>>>() {
        }).getBody().stream()
                .map(this::parseGroup)
                .collect(Collectors.toSet());
    }

    private Group parseGroup(Map<String, String> map) {
        String name = map.get("name");
        return new Group(map.get("urn"), name, name, "Invite", Membership.MEMBER);
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
