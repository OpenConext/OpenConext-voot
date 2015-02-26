package voot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import voot.valueobject.Group;

public class ExternalGroupsQuerierTest {

  @Test(expected = IllegalArgumentException.class)
  public void mustHaveClientsConfigured() {
    new ExternalGroupsQuerier(Collections.emptyList());
  }


  @Test
  public void testAllCompleteInTimeWithSingleResult() throws Exception {
    List<GroupClient> groupClients = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> groupClients.add(new MockGroupClient(200L, false)));
    ExternalGroupsQuerier externalGroupsQuerier = new ExternalGroupsQuerier(groupClients);
    final List<Group> result = externalGroupsQuerier.getMyGroups("foo", "example.com");
    assertTrue(result.size() == groupClients.size());
  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<GroupClient> groupClients = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach(i -> groupClients.add(new MockGroupClient(200L, i % 2 == 0 ? true : false)));
    ExternalGroupsQuerier externalGroupsQuerier = new ExternalGroupsQuerier(groupClients);
    final List<Group> foo = externalGroupsQuerier.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 5);
  }
}
