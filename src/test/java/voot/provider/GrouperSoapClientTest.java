package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GrouperSoapClientTest {

  private static final String SURFNET_SCHAC = "surfnet.nl";
  private GrouperSoapClient subject = getSubject("http://localhost:8089/grouper-ws/services/GrouperService_v2_0");

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8089);

  @Test
  @Ignore // only used for development purposes.
  public void testGetMembershipsFromVM() throws Exception {
    final List<Group> memberships = getVMSubject().getMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.size() == 2);
  }

  @Test
  public void testGetMemberships() throws Exception {
    String response = StreamUtils.copyToString(new ClassPathResource("soap/GetGroupsResponse.xml").getInputStream(), Charset.forName("UTF-8"));
    stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0")).willReturn(aResponse().withStatus(200).withBody(response)));
    final List<Group> memberships = subject.getMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.size() == 2);

    assertTrue(memberships.get(0).displayName.equals("system administrators with all privileges"));
    assertTrue(memberships.get(0).id.equals("etc:sysadmingroup"));

    assertTrue(memberships.get(1).displayName.equals("test groep"));
    assertTrue(memberships.get(1).id.equals("nl:surfnet:diensten:test_groep"));

  }

  private GrouperSoapClient getSubject(String url) {
    Provider.Configuration.Credentials credentials = new Provider.Configuration.Credentials("gadget", "gadget");
    String schac = SURFNET_SCHAC;
    return new GrouperSoapClient(new Provider.Configuration(url, credentials, 2000, schac));

  }

  private GrouperSoapClient getVMSubject() {
    String url = "https://grouper.vm.openconext.org/grouper-ws/services/GrouperService_v2_0";
    return getSubject(url);

  }

}
