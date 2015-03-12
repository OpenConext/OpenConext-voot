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
  public void testShouldBeQueriedFor() throws Exception {
    assertTrue(subject.shouldBeQueriedForMemberships("whatever.nl"));
    AbstractProvider voot = new MockProvider(1L, false, GroupProviderType.VOOT1);
    assertTrue(voot.shouldBeQueriedForMemberships(MockProvider.SCHAC_HOME_ORGANIZATION));
    assertFalse(voot.shouldBeQueriedForMemberships("whatever.nl"));
  }
}
