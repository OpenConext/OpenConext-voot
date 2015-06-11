package voot.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class UrnUtilsTest {

  @Test
  public void testStripGroupUrnIdentifier() {
    assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").get().equals("nl:surfnet:diensten:apachecon"));
    assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:example!org:remainder").get().equals("remainder"));
    assertFalse(UrnUtils.extractLocalGroupId("example.com").isPresent());
  }

  @Test
  public void testStripPersonUrnIdentifier() {
    assertTrue(UrnUtils.extractLocalUid("urn:collab:person:example.com:admin").get().equals("admin"));
    assertFalse(UrnUtils.extractLocalUid("admin").isPresent());
  }

  @Test
  public void testIsFullyQualifiedGroupName() {
    assertTrue(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon"));
    assertTrue(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:whatever:1"));
    assertFalse(UrnUtils.isFullyQualifiedGroupName("urn:collab:group::1"));
    assertFalse(UrnUtils.isFullyQualifiedGroupName("urn:collab:group:1:"));
  }

  @Test
  public void testGetSchacHomeFromGroupUrn() {
    assertTrue(UrnUtils.getSchacHomeFromGroupUrn("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").get().equals("surfteams.nl"));
    assertTrue(UrnUtils.getSchacHomeFromGroupUrn("urn:collab:group:example!org:remainder").get().equals("example!org"));
  }

  @Test
  public void testGetSchacHomeFromGroupUrnIllegal() {
    assertFalse(UrnUtils.getSchacHomeFromGroupUrn("surfteams.nl").isPresent());
  }

  @Test
  public void testGetSchacHomeFromPersonUrn() {
    assertTrue(UrnUtils.getSchacHomeFromPersonUrn("urn:collab:person:example.com:some:admin").get().equals("example.com"));
    assertTrue(UrnUtils.getSchacHomeFromPersonUrn("urn:collab:person:example!org:remainder").get().equals("example!org"));
  }

  @Test
  public void testGetSchacHomeFromPersonIllegal() {
    assertFalse(UrnUtils.getSchacHomeFromGroupUrn("example.org").isPresent());
  }
}
