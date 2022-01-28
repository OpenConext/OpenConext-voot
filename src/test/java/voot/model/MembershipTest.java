package voot.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static voot.model.Membership.*;

class MembershipTest {

    @Test
    void testEquals() {
        Membership test = new Membership("test");
        Membership other = new Membership("test");
        assertEquals(test, other);
        assertEquals(new HashSet<>(Arrays.asList(test, other)).size(), 1);
    }

    @Test
    void testCompareTo() {
        Membership owner = new Membership("owner");
        assertEquals(Arrays.asList(
                owner,
                new Membership("prospect"),
                ADMIN, MANAGER, MEMBER).stream().max(Comparator.naturalOrder()).get(), owner);
        assertEquals("Membership{basic='admin'}", ADMIN.toString());
    }
}
