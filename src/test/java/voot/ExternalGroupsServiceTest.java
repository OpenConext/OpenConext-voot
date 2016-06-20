package voot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static voot.MockProvider.SimulationMode.Error;
import static voot.MockProvider.SimulationMode.Success;
import static voot.MockProvider.SimulationMode.Timeout;
import static voot.provider.GroupProviderType.GROUPER;
import static voot.provider.GroupProviderType.VOOT2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;

import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Member;

public class ExternalGroupsServiceTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    new ExternalGroupsService(Collections.emptyList());
  }

  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, Success, GROUPER)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> result = externalGroupsService.getMyGroups("foo", "example.com", true);
    assertTrue(result.size() == providers.size());
  }

  @Test
  public void testOneCompletesInTimeAndAnotherFails() throws Exception {
    final MockProvider successMockProvider = new MockProvider(200L, Success, GROUPER);
    final MockProvider errorMockProvider = new MockProvider(200L, Error, GROUPER);

    ExternalGroupsService externalGroupsService = new ExternalGroupsService(Arrays.asList(successMockProvider, errorMockProvider));
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com", true);
    assertTrue(foo.size() == 1);
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, i % 2 == 0 ? Success : Timeout, GROUPER)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com", true);
    assertTrue(foo.size() == 5);
  }

  @Test
  public void testGetMyGroupById() throws Exception {
    List<Provider> providers = Collections.singletonList(new MockProvider(200L, Success, VOOT2));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    Optional<Group> group = externalGroupsService.getMyGroupById("admin", "urn:collab:group:example.com:admin-team");
    assertEquals("urn:collab:group:example.com:admin-team", group.get().id);
  }

  @Test
  public void testGetMyExternalGroups() throws Exception {
    List<Provider> providers = Arrays.asList(
      new MockProvider(200L, Success, VOOT2),
      new MockProvider(200L, Timeout, GROUPER),
      new MockProvider(200L, Error, GROUPER));

    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    List<Group> groups = externalGroupsService.getMyExternalGroups("admin", "example.com");
    assertEquals(1, groups.size());
  }

  @Test
  public void testGetMembers() throws Exception {
    List<Provider> providers = Arrays.asList(
      new MockProvider(200L, Success, GROUPER),
      new MockProvider(200L, Timeout, GROUPER),
      new MockProvider(200L, Success, VOOT2),
      new MockProvider(200L, Error, GROUPER));

    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    List<Member> members = externalGroupsService.getMembers("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon");
    assertEquals(1, members.size());
    assertEquals(MockProvider.MEMBER, members.get(0));
  }

  @Test
  public void testGetAllGroups() throws Exception {
    List<Provider> providers = Arrays.asList(
      new MockProvider(200L, Success, GROUPER),
      new MockProvider(200L, Success, VOOT2)
  );

    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    List<Group> allGroups = externalGroupsService.getAllGroups();
    assertEquals(1, allGroups.size());
  }

}
