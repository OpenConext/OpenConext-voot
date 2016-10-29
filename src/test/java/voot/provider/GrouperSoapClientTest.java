package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static voot.provider.GrouperSoapClient.*;

public class GrouperSoapClientTest {

  private GrouperDaoClient grouperDaoClient;

  private GrouperSoapClient subject;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Before
  public void before() {
    grouperDaoClient = mock(GrouperDaoClient.class);
    Provider.Configuration.Credentials credentials = new Provider.Configuration.Credentials("gadget", "gadget");
    Configuration configuration = new Configuration(GroupProviderType.GROUPER, "http://localhost:8889/grouper-ws/services/GrouperService_v2_0", credentials, 2000, "surfnet.nl", "surfnet");
    subject = new GrouperSoapClient(configuration, this.grouperDaoClient);
  }

  @Test
  public void testGrouperError() throws Exception {
    when(grouperDaoClient.groups("urn:collab:person:example.com:admin")).thenThrow(new RuntimeException());
    List<Group> groups = subject.getGroupMemberships("urn:collab:person:example.com:admin");
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
    when(grouperDaoClient.groups("urn:collab:person:example.com:admin")).thenReturn(singletonList(new Group("id", "nice", "desc", "grouper", Membership.ADMIN)));
    List<Group> memberships = subject.getGroupMemberships("urn:collab:person:example.com:admin");

    assertTrue(memberships.size() == 1);
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

  private void stubGrouperCall(String responseFile, String soupAction) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    wireMockRule.stubFor(post(urlEqualTo("/grouper-ws/services/GrouperService_v2_0"))
      .withHeader("Content-Type", equalTo("text/xml"))
      .withHeader(GrouperSoapClient.SOAP_ACTION, equalTo(soupAction))
      .willReturn(aResponse().withStatus(200).withBody(response)));
  }

}
