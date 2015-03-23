package voot.provider;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

public class AbstractProviderTest extends TestCase {

  @Test
  public void testStripGroupUrnIdentifier() throws Exception {
    assertTrue(AbstractProvider.stripGroupUrnIdentifier("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("nl:surfnet:diensten:apachecon"));
    assertTrue(AbstractProvider.stripGroupUrnIdentifier("urn:collab:group:example!org:remainder").equals("remainder"));
    assertTrue(AbstractProvider.stripGroupUrnIdentifier("example.com").equals("example.com"));
  }

  @Test
  public void testStripPersonUrnIdentifier() throws Exception {
    assertTrue(AbstractProvider.stripPersonUrnIdentifier("urn:collab:person:example.com:admin").equals("admin"));
    assertTrue(AbstractProvider.stripPersonUrnIdentifier("admin").equals("admin"));
  }

  @Test
  public void testIsFullyQualifiedGroupName() {
    assertTrue(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon"));
    assertTrue(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:whatever:1"));
    assertFalse(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group::1"));
    assertFalse(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:1:"));
  }

  @Test
  public void testGetSchacHomeFromGroupUrn() {
    assertTrue(AbstractProvider.getSchacHomeFromGroupUrn("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("surfteams.nl"));
    assertTrue(AbstractProvider.getSchacHomeFromGroupUrn("urn:collab:group:example!org:remainder").equals("example!org"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSchacHomeFromGroupUrnIllegal() {
    AbstractProvider.getSchacHomeFromGroupUrn("surfteams.nl");
  }
}
