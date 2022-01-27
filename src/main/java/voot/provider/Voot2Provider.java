package voot.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import voot.model.Group;
import voot.model.Member;
import voot.model.Membership;
import voot.util.UrnUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

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
        String localUid = personUrnFromFullyQualifiedUrn(uid);
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(allMembershipsUrlTemplate, configuration.url), String.class, localUid);
        return handleResponse(response, this::parseGroups, "getGroupMemberships", emptyList());
    }

    @Override
    public List<Group> getAllGroups() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Group> getGroupMembership(final String uid, final String groupId) {
        LOG.debug("Querying getGroupMembership for subjectId: {} and name: {}", uid, configuration.schacHomeOrganization);

        String localUid = personUrnFromFullyQualifiedUrn(uid);
        String localGroupId = UrnUtils.extractLocalGroupId(groupId);

        final String url = String.format(specificMembershipTemplate, configuration.url);

        LOG.debug("Invoking {} on provider {}", url, this);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, localUid, localGroupId);

        return handleResponse(response, this::parseSingleGroup, "getGroupMembership", Optional.empty());
    }

    /**
     * Default VOOT providers use the local part of the urn
     */
    protected String personUrnFromFullyQualifiedUrn(String uid) {
        return UrnUtils.extractLocalUid(uid);
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
        return maps.stream().map(this::parseGroup).collect(toList());
    }

    @SuppressWarnings("unchecked")
    protected Optional<Group> parseSingleGroup(String response) {
        return Optional.of(this.parseGroup(this.parseJson(response, Map.class)));
    }

    private Group parseGroup(Map<String, Object> item) {
        return new Group(
                groupIdPrefix + item.get("id"),
                (String) item.get("displayName"),
                (String) item.get("description"),
                configuration.name,
                parseMembership(item.get("membership")));
    }

    private Membership parseMembership(Object membership) {
        if (membership instanceof String) {
            return new Membership(String.class.cast(membership));
        }
        if (membership instanceof Map) {
            return new Membership(String.class.cast(Map.class.cast(membership).get("basic")));
        }
        return Membership.MEMBER;
    }


}
