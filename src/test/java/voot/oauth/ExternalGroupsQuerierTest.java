package voot.oauth;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import voot.oauth.valueobject.Group;

public class ExternalGroupsQuerierTest {

  @Test
  public void testAllCompleteInTime() throws Exception {
    List<GroupClient> groupClients = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach( i -> groupClients.add(new MockGroupClient(201L,true)));
    ExternalGroupsQuerier externalGroupsQuerier = new ExternalGroupsQuerier(groupClients);
    final List<Group> foo = externalGroupsQuerier.getMyGroups("foo", "example.com");
    assertTrue(foo.isEmpty());

  }

  @Test
  public void testSomeCompleteInTime() throws Exception {
    List<GroupClient> groupClients = new ArrayList<>();
    IntStream.rangeClosed(1, 10).forEach( i -> groupClients.add(new MockGroupClient(201L, i % 2 == 0? true: false)));
    ExternalGroupsQuerier externalGroupsQuerier = new ExternalGroupsQuerier(groupClients);
    final List<Group> foo = externalGroupsQuerier.getMyGroups("foo", "example.com");
    assertTrue(foo.size() == 5);
  }
}
