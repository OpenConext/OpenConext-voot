package voot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import voot.provider.Provider;
import voot.valueobject.Group;

public class ExternalGroupsServiceTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    new ExternalGroupsService(Collections.emptyList());
  }


  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, false)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> result = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(result.size() == providers.size());
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<Provider> providers = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> providers.add(new MockProvider(200L, i % 2 == 0)));
    ExternalGroupsService externalGroupsService = new ExternalGroupsService(providers);
    final List<Group> foo = externalGroupsService.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 5);
  }
}
