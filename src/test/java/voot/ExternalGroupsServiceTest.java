package voot;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static voot.MockProvider.SimulationMode.*;
import static voot.provider.GroupProviderType.GROUPER;
import static voot.provider.GroupProviderType.OPEN_SOCIAL_MEMBERS;
import static voot.provider.GroupProviderType.VOOT2;

import java.util.*;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.provider.TeamsDao;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

public class ExternalGroupsServiceTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    externalGroupService(Collections.emptyList());
  }

  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, Success, GROUPER)));
    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    final List<Group> result = externalGroupsService.getMyGroups("foo", "example.com");
    assertEquals(result.size(), providers.size());
  }

  @Test
  public void testOneCompletesInTimeAndAnotherFails() throws Exception {
    final MockProvider successMockProvider = new MockProvider(200L, Success, GROUPER);
    final MockProvider errorMockProvider = new MockProvider(200L, Error, GROUPER);

    ExternalGroupsService externalGroupsService = externalGroupService(asList(successMockProvider, errorMockProvider));
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 1);
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, i % 2 == 0 ? Success : Timeout, GROUPER)));
    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
    assertEquals(foo.size(), 5);
  }

  @Test
  public void testGetMyGroupById() throws Exception {
    List<Provider> providers = Collections.singletonList(new MockProvider(200L, Success, VOOT2));
    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Optional<Group> group = externalGroupsService.getMyGroupById("admin", "urn:collab:group:example.com:admin-team");
    assertTrue(group.get().id.contains("urn:collab:group:example.com:admin-team"));
  }

  @Test
  public void testGetMyExternalGroups() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, VOOT2),
      new MockProvider(200L, Timeout, GROUPER),
      new MockProvider(200L, Error, GROUPER));

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    List<Group> groups = externalGroupsService.getMyExternalGroups("admin", "example.com");
    assertEquals(1, groups.size());
  }

  @Test
  public void testGetMembers() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, GROUPER),
      new MockProvider(200L, Timeout, GROUPER),
      new MockProvider(200L, Success, VOOT2),
      new MockProvider(200L, Error, GROUPER));

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    List<Member> members = externalGroupsService.getMembers("urn:collab:group:example.org:nl:surfnet:diensten:apachecon");
    assertEquals(2, members.size());
    assertEquals(MockProvider.MEMBER, members.get(0));
  }

  @Test
  public void testGetAllGroups() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, GROUPER),
      new MockProvider(200L, Success, VOOT2)
    );

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    List<Group> allGroups = externalGroupsService.getAllGroups();
    assertEquals(1, allGroups.size());
  }

  @Test
  public void testGetMembersIncExternal() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, OPEN_SOCIAL_MEMBERS)
    );

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    List<Member> members = externalGroupsService.getMembers("personId",
      "urn:collab:group:example.org:DIF-uc-test-surfteams-teams-3_grp");

    assertEquals(1, members.size());
    assertEquals(MockProvider.MEMBER, members.get(0));
  }

  @Test
  public void testFilterDuplicatesWithLowerImportance() {
    ExternalGroupsService externalGroupsService = externalGroupService(asList(
      new MockProvider(200L, Success, GROUPER)));
    List<Group> groups = externalGroupsService.filterDuplicatesWithLowerImportance(asList(
      new Group("id1", null, null, null, Membership.ADMIN),
      new Group("id2", null, null, null, Membership.MANAGER),
      new Group("id2", null, null, null, Membership.ADMIN),
      new Group("id2", null, null, null, Membership.MEMBER),
      new Group("id3", null, null, null, Membership.MANAGER),
      new Group("id3", null, null, null, Membership.MEMBER)
    ));
    groups.sort(Comparator.comparing(group -> group.id));

    assertEquals(3, groups.size());
    assertEquals(asList(
      new Group("id1", null, null, null, Membership.ADMIN),
      new Group("id2", null, null, null, Membership.ADMIN),
      new Group("id3", null, null, null, Membership.MANAGER)
    ), groups);
  }

  private ExternalGroupsService externalGroupService(List<Provider> providers) {
    return externalGroupService(providers, true);
  }

  private ExternalGroupsService externalGroupService(List<Provider> providers, boolean supportLinkedGrouperExternalGroups) {
    return new ExternalGroupsService(providers, mock(TeamsDao.class), supportLinkedGrouperExternalGroups);
  }

}
