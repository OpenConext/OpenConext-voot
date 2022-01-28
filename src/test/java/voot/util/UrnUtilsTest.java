package voot.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static voot.util.UrnUtils.getSchacHomeFromGroupUrn;
import static voot.util.UrnUtils.getSchacHomeFromPersonUrn;

class UrnUtilsTest {

    @Test
    void testStripGroupUrnIdentifier() {
        assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("nl:surfnet:diensten:apachecon"));
        assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:example!org:remainder").equals("remainder"));
    }

    @Test
    void testStripGroupUrnIdentifierInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UrnUtils.extractLocalGroupId("example.com"));
    }

    @Test
    void testStripPersonUrnIdentifier() {
        assertTrue(UrnUtils.extractLocalUid("urn:collab:person:example.com:admin").equals("admin"));
    }

    @Test
    void testStripPersonUrnIdentifierInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UrnUtils.extractLocalUid("admin"));
    }

    @Test
    void testIsFullyQualifiedGroupName() {
        assertTrue(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon"));
        assertTrue(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:whatever:1"));
        assertFalse(UrnUtils.isFullyQualifiedGroupName("urn:collab:group::1"));
        assertFalse(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:1:"));
    }

    @Test
    void testGetSchacHomeFromGroupUrn() {
        assertTrue(getSchacHomeFromGroupUrn("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("surfteams.nl"));
        assertTrue(getSchacHomeFromGroupUrn("urn:collab:group:example!org:remainder").equals("example!org"));
    }

    @Test
    void testGetSchacHomeFromGroupUrnIllegal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> getSchacHomeFromGroupUrn("surfteams.nl"));
    }

    @Test
    void testGetSchacHomeFromPersonUrn() {
        String schacHomeFromPersonUrn = getSchacHomeFromPersonUrn("urn:collab:person:example.com:some:admin");
        assertTrue(schacHomeFromPersonUrn.equals("example.com"));
        assertTrue(getSchacHomeFromPersonUrn("urn:collab:person:example!org:remainder").equals("example!org"));
    }

    @Test
    void testGetSchacHomeFromPersonIllegal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> getSchacHomeFromGroupUrn("example.org"));
    }
}
