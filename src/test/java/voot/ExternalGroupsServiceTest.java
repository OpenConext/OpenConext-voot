package voot;

import org.junit.Test;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static voot.MockProvider.SimulationMode.Error;
import static voot.MockProvider.SimulationMode.Success;
import static voot.MockProvider.SimulationMode.Timeout;
import static voot.provider.GroupProviderType.OPEN_SOCIAL_MEMBERS;
import static voot.provider.GroupProviderType.TEAMS;
import static voot.provider.GroupProviderType.VOOT2;

public class ExternalGroupsServiceTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    externalGroupService(Collections.emptyList());
  }

  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, Success, TEAMS)));
    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Group> result = externalGroupsService.getMyGroups("foo", "example.com");
    assertEquals(result.size(), providers.size());
  }

  @Test
  public void testOneCompletesInTimeAndAnotherFails() throws Exception {
    final MockProvider successMockProvider = new MockProvider(200L, Success, TEAMS);
    final MockProvider errorMockProvider = new MockProvider(200L, Error, TEAMS);

    ExternalGroupsService externalGroupsService = externalGroupService(asList(successMockProvider, errorMockProvider));
    Set<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 1);
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, i % 2 == 0 ? Success : Timeout, TEAMS)));
    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
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
      new MockProvider(200L, Timeout, TEAMS),
      new MockProvider(200L, Error, TEAMS));

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Group> groups = externalGroupsService.getMyExternalGroups("admin", "example.com");
    assertEquals(1, groups.size());
  }

  @Test
  public void testGetMembers() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, TEAMS),
      new MockProvider(200L, Timeout, TEAMS),
      new MockProvider(200L, Success, VOOT2),
      new MockProvider(200L, Error, TEAMS));

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Member> members = externalGroupsService.getMembers("urn:collab:group:example.org:nl:surfnet:diensten:apachecon");
    assertEquals(2, members.size());
    assertEquals("urn:collab:person:example.com:admin", members.stream().findFirst().get().id);
  }

  @Test
  public void testGetAllGroups() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, TEAMS),
      new MockProvider(200L, Success, VOOT2)
    );

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Group> allGroups = externalGroupsService.getAllGroups();
    assertEquals(1, allGroups.size());
  }

  @Test
  public void testGetMembersIncExternal() throws Exception {
    List<Provider> providers = asList(
      new MockProvider(200L, Success, OPEN_SOCIAL_MEMBERS)
    );

    ExternalGroupsService externalGroupsService = externalGroupService(providers);
    Set<Member> members = externalGroupsService.getMembers("personId",
      "urn:collab:group:example.org:DIF-uc-test-surfteams-teams-3_grp");

    assertEquals(1, members.size());
    assertEquals("urn:collab:person:example.com:admin", members.stream().findFirst().get().id);
  }

  @Test
  public void testFilterDuplicatesWithLowerImportance() {
    ExternalGroupsService externalGroupsService = externalGroupService(asList(
      new MockProvider(200L, Success, TEAMS)));
    List<Group> groups = new ArrayList(externalGroupsService.filterDuplicatesWithLowerImportance(asList(
      group("id1", Membership.ADMIN),
      group("id2", Membership.MANAGER),
      group("id2", Membership.ADMIN),
      group("id2", Membership.MEMBER),
      group("id3", Membership.MANAGER),
      group("id3", Membership.MEMBER)
    )));
    assertEquals(3, groups.size());

    groups.sort(Comparator.comparing(group -> group.id));
    assertEquals(asList(
      group("id1", Membership.ADMIN),
      group("id2", Membership.ADMIN),
      group("id3", Membership.MANAGER)
    ), groups);
  }

  private Group group(String id, Membership membership) {
    return new Group(id, null, null, null, membership);
  }

  private ExternalGroupsService externalGroupService(List<Provider> providers) {
    return externalGroupService(providers, true);
  }

  private ExternalGroupsService externalGroupService(List<Provider> providers, boolean supportLinkedGrouperExternalGroups) {
    return new ExternalGroupsService(providers, supportLinkedGrouperExternalGroups);
  }

}
