package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;
import static voot.provider.GrouperSoapClient.*;

public class GrouperSoapClientTest {

  private GrouperSoapClient subject = getSubject("http://localhost:8889/grouper-ws/services/GrouperService_v2_0");

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Before
  public void before() {
    wireMockRule.resetToDefaultMappings();
  }

  @Test
  @Ignore // only used for development purposes.
  public void testGetMembershipsFromVM() throws Exception {
    List<Group> memberships = getVMSubject().getGroupMemberships("urn:collab:person:example.com:admin", true);
    assertTrue(memberships.size() == 2);
  }

  @Test
  public void testGrouperError() throws Exception {
    stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0")).withHeader("Content-Type", equalTo("text/xml")).willReturn(aResponse().withStatus(500)));
    List<Group> groups = subject.getGroupMemberships("urn:collab:person:example.com:admin", true);
    assertTrue(groups.isEmpty());
  }

  @Test
  public void testShouldBeQueriedFor() throws Exception {
    assertTrue(subject.shouldBeQueriedForMemberships("surfnet.nl"));
    assertTrue(subject.shouldBeQueriedForMemberships("whatever.org"));

    assertFalse(subject.shouldBeQueriedForGroup("must.be.urn"));
    assertFalse(subject.shouldBeQueriedForGroup("urn:collab:group:example.org:a"));

    assertTrue(subject.shouldBeQueriedForGroup("urn:collab:group:" + subject.configuration.schacHomeOrganization + ":a"));
  }


  @Test
  public void testGetMemberships() throws Exception {
    stubGrouperCall("soap/GetGroups_Success_Response.xml", URN_GET_GROUPS_LITE);
    stubGrouperCall("soap/GetPrivileges_Success_Response.xml", URN_GET_GROUPER_PRIVILEGES_LITE);

    List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin", true);

    //have to sleep otherwise the wireMock servers stops before the privileges are fetched in parallel
    Thread.sleep(1500);

    assertTrue(memberships.size() == 12);

    Group group1 = memberships.get(0);
    assertEquals(group1.id,"urn:collab:group:surfnet.nl:etc:sysadmingroup");
    assertEquals(group1.displayName,"sysadmingroup");
    assertEquals(group1.description,"system administrators with all privileges");
    assertEquals(group1.membership.getBasic(),"admin");


    Group group2 = memberships.get(1);
    assertEquals(group2.id,"urn:collab:group:surfnet.nl:nl:surfnet:diensten:test_groep_1");
    assertEquals(group2.displayName,"test_groep_1");
    assertEquals(group2.description,"test groep 1");
    assertEquals(group2.membership.getBasic(),"admin");
  }

  @Test
  public void testGetMembershipsEmptyResult() throws Exception {
    stubGrouperCall("soap/GetGroups_Empty_Response.xml", URN_GET_GROUPS_LITE);
    List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin", true);
    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetMembershipForMember() throws Exception {
    stubGrouperCall("soap/HasMemberLite_Member_Response.xml", URN_HAS_MEMBER_LITE);

    Optional<Group> optionalGroup = subject.getGroupMembership("urn:collab:person:example.com:admin", "urn:collab:group:surfnet.nl:some_group");
    assertTrue(optionalGroup.isPresent());

    Group group = optionalGroup.get();
    assertTrue(group.id.equals("urn:collab:group:surfnet.nl:nl:surfnet:diensten:dit_is_de_team_naam"));
    assertTrue(group.displayName.equals("Dit is de team naam"));
    assertTrue(group.description.equals("Dit is de team description, kan lang zijn"));
    assertTrue(group.membership.getBasic().equals("member"));
  }

  @Test
  public void testGetMembershipForNonMember() throws Exception {
    stubGrouperCall("soap/HasMemberLite_NotMember_Response.xml", URN_HAS_MEMBER_LITE);

    Optional<Group> optionalGroup = subject.getGroupMembership("urn:collab:person:example.com:admin", "urn:collab:group:surfnet.nl:some_group");
    assertFalse(optionalGroup.isPresent());
  }

  @Test
  public void testGetMembers() throws Exception {
    stubGrouperCall("soap/GetMembersLite_Success_Response.xml", URN_GET_MEMBERS_LITE);
    List<Member> members = subject.getMembers("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon");
    assertEquals(2, members.size());
    Member bas = members.get(0);
    assertEquals(bas, new Member("urn:collab:person:surfnet.nl:bas","Bas Zoetekouw","bas.zoetekouw@surfnet.nl"));

    Member john = members.get(1);
    assertEquals(john, new Member("urn:collab:person:example.com:admin","John Doe","j.doe@example.com"));
  }

  @Test
  public void testGetMembersEmptyResult() throws Exception {
    stubGrouperCall("soap/GetMembersLite_Empty_Response.xml", URN_GET_MEMBERS_LITE);
    List<Member> members = subject.getMembers("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon");
    assertTrue(members.isEmpty());
  }

  @Test
  public void testGetAllGroups() throws Exception {
    stubGrouperCall("soap/FindGroupsLite_Success_Response.xml", URN_FIND_GROUPS_LITE);
    List<Group> allGroups = subject.getAllGroups();
    assertEquals(47, allGroups.size());
    allGroups.forEach(this::validGroup);
  }

  private void validGroup(Group group) {
    assertTrue(group.id.startsWith("urn:collab:group:surfnet.nl:nl:surfnet:diensten:"));
    assertEquals("surfnet", group.sourceID);
    assertNotNull(group.displayName);
  }

  @Test
  public void testMembersError() throws Exception {
    stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0")).withHeader("Content-Type", equalTo("text/xml")).willReturn(aResponse().withStatus(500)));
    List<Member> members = subject.getMembers("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon");
    assertTrue(members.isEmpty());
  }

  @Test
  public void testCorrectMembershipManager() throws Exception {
    doTestCorrectMembership("soap/GetPrivilegesManager_Success_Response.xml", "manager");
  }

  @Test
  public void testCorrectMembershipMember() throws Exception {
    doTestCorrectMembership("soap/GetPrivilegesMember_Success_Response.xml", "member");
  }

  private void doTestCorrectMembership(String responseFile, String expectedMembership) throws IOException {
    Group group = new Group("id", "displayName", "description", "sourceID", null);
    stubGrouperCall(responseFile, URN_GET_GROUPER_PRIVILEGES_LITE);
    group = subject.correctMembership(group, "urn:collab:person:example.com:admin");

    assertEquals(expectedMembership, group.membership.getBasic());
  }

  private void stubGrouperCall(String responseFile, String soupAction) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    wireMockRule.stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0"))
      .withHeader("Content-Type", equalTo("text/xml"))
      .withHeader(GrouperSoapClient.SOAP_ACTION, equalTo(soupAction))
      .willReturn(aResponse().withStatus(200).withBody(response)));
  }

  private GrouperSoapClient getSubject(String url) {
    Provider.Configuration.Credentials credentials = new Provider.Configuration.Credentials("gadget", "gadget");
    return new GrouperSoapClient(new Provider.Configuration(GroupProviderType.GROUPER, url, credentials, 2000, "surfnet.nl", "surfnet"));

  }

  private GrouperSoapClient getVMSubject() {
    String url = "https://grouper.vm.openconext.org/grouper-ws/services/GrouperService_v2_0";
    return getSubject(url);

  }

}
