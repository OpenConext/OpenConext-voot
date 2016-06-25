package voot.valueobject;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static voot.valueobject.Membership.ADMIN;
import static voot.valueobject.Membership.MANAGER;
import static voot.valueobject.Membership.MEMBER;

public class MembershipTest {

  @Test
  public void testEquals() throws Exception {
    Membership test = new Membership("test");
    Membership other = new Membership("test");
    assertEquals(test, other);
    assertEquals(new HashSet<>(Arrays.asList(test, other)).size(), 1);
  }

  @Test
  public void testCompareTo() throws Exception {
    assertEquals(Arrays.asList(ADMIN, MANAGER, MEMBER).stream().max((m1, m2) -> m1.compareTo(m2)).get(), ADMIN);
  }
}
