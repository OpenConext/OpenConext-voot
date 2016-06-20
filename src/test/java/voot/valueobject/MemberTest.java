package voot.valueobject;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MemberTest {

  @Test
  public void testEquals() {
    Member m1 = new Member("id", "name", "email");
    Member m2 = new Member("id", "name", "email");

    assertEquals(m1,m2);

    Set<Member> set = new HashSet<>(Arrays.asList(m1,m2));
    assertEquals(1, set.size());
  }

}
