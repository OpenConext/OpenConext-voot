package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

public class GrouperSoapClientTest {

  private GrouperSoapClient subject = getSubject("http://localhost:8889/grouper-ws/services/GrouperService_v2_0");

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Test
  @Ignore // only used for development purposes.
  public void testGetMembershipsFromVM() throws Exception {
    final List<Group> memberships = getVMSubject().getGroupMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.size() == 2);
  }

  @Test
  public void testGrouperError() throws Exception {
    stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0")).withHeader("Content-Type", equalTo("text/xml")).willReturn(aResponse().withStatus(500)));
    final List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetMemberships() throws Exception {
    stubGrouperCall("soap/GetGroups_Success_Response.xml");
    final List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.size() == 2);

    Group group1 = memberships.get(0);
    assertTrue(group1.id.equals("urn:collab:group:surfnet.nl:etc:sysadmingroup"));
    assertTrue(group1.displayName.equals("sysadmingroup"));
    assertTrue(group1.description.equals("system administrators with all privileges"));
    assertTrue(group1.membership.basic.equals("member"));


    Group group2 = memberships.get(1);
    assertTrue(group2.id.equals("urn:collab:group:surfnet.nl:nl:surfnet:diensten:test_groep"));
    assertTrue(group2.displayName.equals("test_groep"));
    assertTrue(group2.description.equals("test groep"));
    assertTrue(group2.membership.basic.equals("member"));
  }

  @Test
  public void testGetMembershipsEmptyResult() throws Exception {
    stubGrouperCall("soap/GetGroups_Empty_Response.xml");
    final List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin");
    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetMembershipForMember() throws Exception {
    stubGrouperCall("soap/HasMemberLite_Member_Response.xml");

    Optional<Group> optionalGroup = subject.getGroupMembership("urn:collab:person:example.com:admin", "urn:collab:group:surfnet.nl:some_group");
    assertTrue(optionalGroup.isPresent());

    Group group = optionalGroup.get();
    assertTrue(group.id.equals("urn:collab:group:surfnet.nl:nl:surfnet:diensten:dit_is_de_team_naam"));
    assertTrue(group.displayName.equals("Dit is de team naam"));
    assertTrue(group.description.equals("Dit is de team description, kan lang zijn"));
    assertTrue(group.membership.basic.equals("member"));
  }

  @Test
  public void testGetMembershipForNonMember() throws Exception {
    stubGrouperCall("soap/HasMemberLite_NotMember_Response.xml");

    Optional<Group> optionalGroup = subject.getGroupMembership("urn:collab:person:example.com:admin", "urn:collab:group:surfnet.nl:some_group");
    assertFalse(optionalGroup.isPresent());
  }

  private void stubGrouperCall(String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0")).withHeader("Content-Type", equalTo("text/xml")).willReturn(aResponse().withStatus(200).withBody(response)));
  }

  private GrouperSoapClient getSubject(String url) {
    Provider.Configuration.Credentials credentials = new Provider.Configuration.Credentials("gadget", "gadget");
    return new GrouperSoapClient(new Provider.Configuration(GroupProviderType.GROUPER, url, credentials, 2000, "surfnet.nl"));

  }

  private GrouperSoapClient getVMSubject() {
    String url = "https://grouper.vm.openconext.org/grouper-ws/services/GrouperService_v2_0";
    return getSubject(url);

  }

}
