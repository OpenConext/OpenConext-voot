package voot.provider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import voot.model.Group;
import voot.model.Member;
import voot.util.UrnUtils;

import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

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
    public Set<Group> linkedLocalTeamsGroup(Collection<String> fullyQualifiedExternalGroupIds) {
        //Make the externalGroupIdentifiers unique
        HashSet<String> uniqueGroupIds = new HashSet<>(fullyQualifiedExternalGroupIds);
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/linked-locals", configuration.url))
                .queryParam("externalGroupIds", String.join(",", uniqueGroupIds))
                .build().encode().toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return handleResponse(response, this::parseGroups, "findByLocalGroupId", emptySet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<String> linkedExternalGroupIds(String localGroupId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/linked-externals", configuration.url))
                .queryParam("teamId", localGroupId)
                .build().encode().toUri();
        ResponseEntity<Set> response = restTemplate.getForEntity(uri, Set.class);
        return handleResponse(response, s -> s, "findByLocalGroupId", emptySet());
    }

    @Override
    public Set<Member> getMembers(String personId, String groupId) {
        return getMembers(groupId);
    }

    @Override
    public Set<Member> getMembers(String groupId) {
        String uri = String.format("%s/members/{groupId}", configuration.url);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, groupId);
        return handleResponse(response, this::parseMembers, "getMembers", emptySet());
    }

    @Override
    public Set<Group> getAllGroups() {
        String uri = String.format("%s/groups", configuration.url);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return handleResponse(response, this::parseGroups, "getAllGroups", emptySet());
    }

    /**
     * Teams use the fully qualified person urn
     */
    @Override
    protected String personUrnFromFullyQualifiedUrn(String uid) {
        return uid;
    }

    @SuppressWarnings("unchecked")
    private Set<Member> parseMembers(String response) {
        List<Map<String, String>> maps = parseJson(response, List.class);
        return maps.stream().map(map -> new Member(map.get("id"), map.get("name"), map.get("email"))).collect(Collectors.toSet());
    }

}
