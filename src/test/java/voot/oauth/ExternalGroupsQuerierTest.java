package voot.oauth;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class ExternalGroupsQuerierTest {

  @Test
  public void testAllCompleteInTime() throws Exception {
    List<GroupClient> groupClients = new ArrayList<>();
    IntStream.rangeClosed(0, 10).forEach( i -> groupClients.add(new MockGroupClient()));
    ExternalGroupsQuerier externalGroupsQuerier = new ExternalGroupsQuerier(groupClients, 201L);
    final List<Voot2Group> foo = externalGroupsQuerier.getMyGroups("foo", "example.com");
    assertTrue(foo.isEmpty());

  }
}
