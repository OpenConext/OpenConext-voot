package voot.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    void testEquals() {
        Member m1 = new Member("id", "name", "email");
        Member m2 = new Member("id", "name", "email");

        assertEquals(m1, m2);

        Set<Member> set = new HashSet<>(Arrays.asList(m1, m2));
        assertEquals(1, set.size());
    }

}
