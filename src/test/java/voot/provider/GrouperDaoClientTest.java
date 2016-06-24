package voot.provider;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class GrouperDaoClientTest {

  @Test
  public void testGroups() throws Exception {
    List<Integer> ints = Arrays.asList(1,2,3,4);
    Optional<Integer> max = ints.stream().max((i, j) -> 0);
    System.out.println(max);
  }
}
