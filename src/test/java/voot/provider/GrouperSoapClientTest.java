package voot.provider;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import voot.valueobject.Group;

public class GrouperSoapClientTest {

  private static final String SURFNET_SCHAC = "surfnet.nl";
  private GrouperSoapClient subject;

  @Before
  public void setUp() throws Exception {
    Provider.Configuration.Credentials credentials = new Provider.Configuration.Credentials("gadget", "gadget");
    String url = "https://grouper.vm.openconext.org/grouper-ws/services/GrouperService_v2_0";
    String schac = SURFNET_SCHAC;

    subject = new GrouperSoapClient(new Provider.Configuration(url, credentials, 2000, schac));

  }

  @Test
  @Ignore // only used for development purposes.
  public void testGetMemberships() throws Exception {
    // see
    final List<Group> memberships = subject.getMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.size() == 2);
  }
}
