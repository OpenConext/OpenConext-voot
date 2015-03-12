package voot;

import org.junit.Test;
import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.valueobject.Group;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ExternalGroupsServiceTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    new ExternalGroupsService(Collections.emptyList());
  }

  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, false, GroupProviderType.GROUPER)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> result = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(result.size() == providers.size());
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, i % 2 == 0, GroupProviderType.GROUPER)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 5);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGetMyGroupByIdWithoutFullyQualifiedGroupId() throws Exception {
    List<Provider> providers = Arrays.asList(new MockProvider(200L, false, GroupProviderType.VOOT2));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    externalGroupsService.getMyGroupById("admin", "not:fully:qualified", "example.com");
  }

  @Test
  public void testGetMyGroupById() throws Exception {
    List<Provider> providers = Arrays.asList(new MockProvider(200L, false, GroupProviderType.VOOT2));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    Optional<Group> group = externalGroupsService.getMyGroupById("admin", "urn:collab:group:example.com:admin-team", "example.com");
    assertEquals("urn:collab:group:example.com:admin-team", group.get().id);

  }

}
