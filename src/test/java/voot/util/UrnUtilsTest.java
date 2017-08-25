package voot.util;

import org.junit.Test;
import voot.web.VootController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static voot.util.UrnUtils.getSchacHomeFromGroupUrn;
import static voot.util.UrnUtils.getSchacHomeFromPersonUrn;


public class UrnUtilsTest {

  @Test
  public void testStripGroupUrnIdentifier() {
    assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("nl:surfnet:diensten:apachecon"));
    assertTrue(UrnUtils.extractLocalGroupId("urn:collab:group:example!org:remainder").equals("remainder"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStripGroupUrnIdentifierInvalid() {
    UrnUtils.extractLocalGroupId("example.com");
  }

  @Test
  public void testStripPersonUrnIdentifier() {
    assertTrue(UrnUtils.extractLocalUid("urn:collab:person:example.com:admin").equals("admin"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStripPersonUrnIdentifierInvalid() {
    UrnUtils.extractLocalUid("admin");
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
    assertTrue(getSchacHomeFromGroupUrn("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("surfteams.nl"));
    assertTrue(getSchacHomeFromGroupUrn("urn:collab:group:example!org:remainder").equals("example!org"));
  }

  @Test(expected = VootController.MalformedGroupUrnException.class)
  public void testGetSchacHomeFromGroupUrnIllegal() {
    getSchacHomeFromGroupUrn("surfteams.nl");
  }

  @Test
  public void testGetSchacHomeFromPersonUrn() {
    String schacHomeFromPersonUrn = getSchacHomeFromPersonUrn("urn:collab:person:example.com:some:admin");
    assertTrue(schacHomeFromPersonUrn.equals("example.com"));
    assertTrue(getSchacHomeFromPersonUrn("urn:collab:person:example!org:remainder").equals("example!org"));
  }

  @Test(expected = VootController.MalformedGroupUrnException.class)
  public void testGetSchacHomeFromPersonIllegal() {
    getSchacHomeFromGroupUrn("example.org");
  }
}
