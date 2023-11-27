package voot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voot.model.Group;
import voot.model.Membership;
import voot.provider.Provider;
import voot.provider.TeamsProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static voot.util.UrnUtils.extractLocalGroupId;

/**
 * Functional tests for the LinkedGroups scenario are difficult to test. The LinkedGroups functionality
 * allows linking teams from Teams to external teams from institutions.
 * <p>
 * Prerequisites:
 * Mary is member of the team Teachers from the UvA
 * Joe is member of the team Admins from Teams
 * Team Teachers is linked to the team Admins by someone who is member of both teams (not Mary or Joe)
 * <p>
 * As a result Mary is implicitly also member of the team Admins. Joe is not implicitly also a member of the
 * team Teachers, because the groups are nested and not linked.
 */
class ExternalGroupsServiceLinkedGroupsTest {

    private final String groupIdPrefix = "urn:collab:group:surfnet.test:";
    private final String admins = groupIdPrefix + "Admins";
    private final String teachers = "urn:collab:group:example.org:Teachers";
    private final String adminGrouperId = extractLocalGroupId(admins);

    private ExternalGroupsService subject;

    @BeforeEach
    void before() {

        TeamsProvider teamsProvider = mock(TeamsProvider.class);
        Provider externalGroupProvider = mock(Provider.class);

        when(teamsProvider.shouldBeQueriedForGroup(admins)).thenReturn(true);
        when(teamsProvider.shouldBeQueriedForGroup(teachers)).thenReturn(false);
        when(teamsProvider.shouldBeQueriedForMemberships(anyString())).thenReturn(true);

        when(externalGroupProvider.shouldBeQueriedForGroup(admins)).thenReturn(false);
        when(externalGroupProvider.shouldBeQueriedForGroup(teachers)).thenReturn(true);
        when(externalGroupProvider.shouldBeQueriedForMemberships("surfnet.test")).thenReturn(false);
        when(externalGroupProvider.shouldBeQueriedForMemberships("example.org")).thenReturn(true);

        when(teamsProvider.isTeamsGroup(admins)).thenReturn(true);
        when(teamsProvider.isTeamsGroup(teachers)).thenReturn(false);
        when(teamsProvider.findByLocalGroupId(adminGrouperId)).thenReturn(Optional.of(group(admins)));
        when(teamsProvider.getGroupMemberships("Joe")).thenReturn(Set.of((group(admins))));
        when(teamsProvider.getGroupMemberships("Mary")).thenReturn(Collections.emptySet());
        when(teamsProvider.linkedLocalTeamsGroup(Collections.singletonList(teachers))).thenReturn(Set.of((group(admins))));
        when(teamsProvider.linkedExternalGroupIds("Admins")).thenReturn(Set.of((teachers)));

        when(externalGroupProvider.getGroupMembership("Mary", teachers)).thenReturn(Optional.of(group(teachers)));
        when(externalGroupProvider.getGroupMembership(eq("Joe"), anyString())).thenReturn(Optional.empty());
        when(externalGroupProvider.getGroupMemberships("Mary")).thenReturn(Set.of((group(teachers))));

        when(teamsProvider.getGroupMembership("Joe", admins)).thenReturn(Optional.of(group(admins)));
        when(teamsProvider.getGroupMembership("Mary", admins)).thenReturn(Optional.empty());

        subject = new ExternalGroupsService(asList(teamsProvider, externalGroupProvider), true);
    }

    @Test
    void testGetGrouperTeamLinkedToInstitutionUser() throws Exception {
        Optional<Group> group = subject.getMyGroupById("Mary", admins);
        assertEquals(admins, group.get().id);
    }

    @Test
    void testGetExternalTeamLinkedToGrouperUser() throws Exception {
        Optional<Group> group = subject.getMyGroupById("Joe", teachers);
        assertFalse(group.isPresent());
    }

    @Test
    void testGetExternalTeamsLinkedToGrouperUser() throws Exception {
        Set<Group> groups = subject.getMyGroups("Joe", "surfnet.test");
        assertMyGroupsEquality(groups, admins);
    }

    @Test
    void testGetGrouperTeamsLinkedToExternalUser() throws Exception {
        Set<Group> groups = subject.getMyGroups("Mary", "example.org");
        assertMyGroupsEquality(groups, admins, teachers);
    }

    private void assertMyGroupsEquality(Set<Group> groups, String... names) {
        assertEquals(new HashSet<>(groups.stream().map(group -> group.id).collect(toList())), new HashSet<>(asList(names)));
    }

    private Group group(String id) {
        return new Group(id, "dp", "des", "sId", Membership.MANAGER);
    }

}
