package voot.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static voot.model.Membership.*;

public class MembershipTest {

    @Test
    public void testEquals() {
        Membership test = new Membership("test");
        Membership other = new Membership("test");
        assertEquals(test, other);
        assertEquals(new HashSet<>(Arrays.asList(test, other)).size(), 1);
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(Arrays.asList(ADMIN, MANAGER, MEMBER).stream().max(Comparator.naturalOrder()).get(), ADMIN);
    }
}
