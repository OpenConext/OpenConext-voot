package voot;

import org.junit.Before;
import org.junit.Test;
import voot.provider.GrouperProvider;
import voot.provider.Provider;
import voot.provider.TeamsDao;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static voot.MockProvider.SimulationMode.Error;
import static voot.MockProvider.SimulationMode.*;
import static voot.provider.GroupProviderType.*;
import static voot.util.UrnUtils.extractLocalGroupId;

/**
 * Functional tests for the LinkedGroups scenario are difficult to test. The LinkedGroups functionality
 * allows linking teams from Grouper to external teams from institutions.
 *
 * Prerequisites:
 * Mary is member of the team Teachers from the UvA
 * Joe is member of the team Admins from Grouper
 * Team Teachers is linked to the team Admins by someone who is member of both teams (not Mary or Joe)
 *
 * As a result Mary is implicitly also member of the team Admins. Joe is not implicitly also a member of the
 * team Teachers, because the groups are nested and not linked.
 *
 */
public class ExternalGroupsServiceLinkedGroupsTest {

  private String groupIdPrefix = "urn:collab:group:surfnet.test:";
  private String admins = groupIdPrefix + "Admins";
  private String teachers = "urn:collab:group:example.org:Teachers";
  private String adminGrouperId = extractLocalGroupId(admins).get();

  private TeamsDao teamsDao;
  private GrouperProvider grouperProvider;
  private Provider externalGroupProvider;
  private ExternalGroupsService subject;

  @Before
  public void before() {
    this.teamsDao = mock(TeamsDao.class);

    grouperProvider = mock(GrouperProvider.class);
    externalGroupProvider = mock(Provider.class);

    when(teamsDao.linkedExternalGroups(adminGrouperId)).thenReturn(asList(group(teachers)));
    when(teamsDao.linkedLocalGrouperGroupIds(teachers)).thenReturn(asList(adminGrouperId));

    when(grouperProvider.shouldBeQueriedForGroup(admins)).thenReturn(true);
    when(grouperProvider.shouldBeQueriedForGroup(teachers)).thenReturn(false);
    when(grouperProvider.shouldBeQueriedForMemberships(anyString())).thenReturn(true);

    when(externalGroupProvider.shouldBeQueriedForGroup(admins)).thenReturn(false);
    when(externalGroupProvider.shouldBeQueriedForGroup(teachers)).thenReturn(true);
    when(externalGroupProvider.shouldBeQueriedForMemberships("surfnet.test")).thenReturn(false);
    when(externalGroupProvider.shouldBeQueriedForMemberships("example.org")).thenReturn(true);

    when(grouperProvider.isGrouperGroup(admins)).thenReturn(true);
    when(grouperProvider.isGrouperGroup(teachers)).thenReturn(false);
    when(grouperProvider.getGroupMembershipsForLocalGroupId(adminGrouperId)).thenReturn(Collections.singletonList(group(admins)));
    when(grouperProvider.getGroupMemberships("Joe")).thenReturn(asList(group(admins)));
    when(grouperProvider.getGroupMemberships("Mary")).thenReturn(Collections.<Group>emptyList());
    when(grouperProvider.getGroupIdPrefix()).thenReturn(groupIdPrefix);

    when(externalGroupProvider.getGroupMembership("Mary", teachers)).thenReturn(Optional.of(group(teachers)));
    when(externalGroupProvider.getGroupMembership(eq("Joe"), anyString())).thenReturn(Optional.empty());
    when(externalGroupProvider.getGroupMemberships("Mary")).thenReturn(asList(group(teachers)));

    when(grouperProvider.getGroupMembership("Joe", admins)).thenReturn(Optional.of(group(admins)));
    when(grouperProvider.getGroupMembership("Mary", admins)).thenReturn(Optional.empty());

    subject = new ExternalGroupsService(asList(grouperProvider, externalGroupProvider), teamsDao, true);
  }

  @Test
  public void testGetGrouperTeamLinkedToInstitutionUser() throws Exception {
    Optional<Group> group = subject.getMyGroupById("Mary", admins);
    assertEquals(admins, group.get().id);
  }

  @Test
  public void testGetExternalTeamLinkedToGrouperUser() throws Exception {
    Optional<Group> group = subject.getMyGroupById("Joe", teachers);
    assertFalse(group.isPresent());
  }

  @Test
  public void testGetExternalTeamsLinkedToGrouperUser() throws Exception {
    List<Group> groups = subject.getMyGroups("Joe", "surfnet.test");
    assertMyGroupsEquality(groups, admins);
  }

  @Test
  public void testGetGrouperTeamsLinkedToExternalUser() throws Exception {
    List<Group> groups = subject.getMyGroups("Mary", "example.org");
    assertMyGroupsEquality(groups, admins, teachers);
  }

  private void assertMyGroupsEquality(List<Group> groups, String... names) {
    assertEquals(new HashSet<>(groups.stream().map(group -> group.id).collect(toList())), new HashSet<>(asList(names)));
  }

  private Group group(String id) {
    return new Group(id, "dp", "des", "sId", Membership.MANAGER);
  }

}
