package voot.provider;

import junit.framework.TestCase;
import org.junit.Test;
import voot.MockProvider;

public class AbstractProviderTest extends TestCase {

  private final AbstractProvider subject = new MockProvider(1L, false, GroupProviderType.GROUPER);

  @Test
  public void testStripGroupUrnIdentifier() throws Exception {
    assertTrue(subject.stripGroupUrnIdentifier("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon").equals("nl:surfnet:diensten:apachecon"));
    assertTrue(subject.stripGroupUrnIdentifier("urn:collab:group:example!org:remainder").equals("remainder"));
    assertTrue(subject.stripGroupUrnIdentifier("example.com").equals("example.com"));
  }

  @Test
  public void testStripPersonUrnIdentifier() throws Exception {
    assertTrue(subject.stripPersonUrnIdentifier("urn:collab:person:example.com:admin").equals("admin"));
    assertTrue(subject.stripPersonUrnIdentifier("admin").equals("admin"));
  }

  @Test
  public void testIsFullyQualifiedGroupName() {
    assertTrue(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon"));
    assertTrue(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:whatever:1"));
    assertFalse(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group::1"));
    assertFalse(AbstractProvider.isFullyQualifiedGroupName("urn:collab:group:1:"));
  }

}
