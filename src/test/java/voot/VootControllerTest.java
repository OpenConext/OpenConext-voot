package voot;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;
import voot.web.VootController;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private static final String TOKEN_VALUE = "token_value";
  private static final String GROUP_URN = "urn:collab:group:schachome:nl:surfnet:diensten:foo";

  @Mock
  private OAuth2Request authRequest;

  @Mock
  private SchacHomeAuthentication userAuthentication;

  @Mock
  private OAuth2Authentication authentication;

  @Mock
  private OAuth2AuthenticationDetails authDetails;

  @Mock
  private ExternalGroupsService externalGroupsService;

  private VootController subject;
  private final static String UID = "foo";
  private final static String SCHAC_HOME = "surfnet.nl";

  @Before
  public void before() {
    subject = new VootController(externalGroupsService);

    when(authentication.getName()).thenReturn(UID);
    when(authentication.getUserAuthentication()).thenReturn(userAuthentication);
    when(userAuthentication.getSchacHomeAuthentication()).thenReturn(SCHAC_HOME);
    when(authentication.getDetails()).thenReturn(authDetails);
    when(authDetails.getTokenValue()).thenReturn(TOKEN_VALUE);
    when(authentication.getOAuth2Request()).thenReturn(authRequest);
  }

  @Test
  public void testEmptyMyGroupsResult() throws Exception {
    when(externalGroupsService.getMyGroups(UID, SCHAC_HOME)).thenReturn(Collections.emptyList());
     List<Group> groups = subject.myGroups(authentication);
    assertTrue(groups.size() == 0);
  }

  @Test
  public void testSingleMembershipPositiveResult() {
     Group group = group();
    when(externalGroupsService.getMyGroups(UID, SCHAC_HOME)).thenReturn(singletonList(group));
     List<Group> groups = subject.myGroups(authentication);
    assertTrue(groups.size() > 0);
  }

  @Test(expected = VootController.MalformedGroupUrnException.class)
  public void testSingleMembershipIllegalGroupUrn() throws Exception {
    String nonFullyQualifiedGroupId = "nl:surfnet:diensten:foo";
    subject.specificGroupMembership(nonFullyQualifiedGroupId, authentication);
  }

  @Test(expected = VootController.MalformedPersonUrnException.class)
  public void testInternalGroupsIllegalUserUrn() throws Exception {
    setUpClientCredentials();
    subject.internalGroups("wrong_user_id", authentication);
  }

  @Test(expected = AccessDeniedException.class)
  public void testAccessDeniedException() throws Exception {
    subject.internalGroups("does_not_matter", authentication);
  }

  @Test
  public void testInternalSpecificGroup() throws Exception {
    setUpClientCredentials();
    when(externalGroupsService.getMyGroupById(UID, GROUP_URN)).thenReturn(Optional.of(group()));

    Group group = subject.internalSpecificGroup(UID, GROUP_URN, authentication);
    assertEquals("id",group.id);
  }

  @Test
  public void testExternalGroups() throws Exception {
    setUpClientCredentials();
    when(externalGroupsService.getMyExternalGroups("urn:collab:person:schac:admin", "schac")).thenReturn(singletonList(group()));

    List<Group> groups = subject.externalGroups("urn:collab:person:schac:admin", authentication);
    assertEquals(1,groups.size());
  }

  @Test(expected = VootController.MalformedPersonUrnException.class)
  public void testExternalGroupsWrongUrn() throws Exception {
    setUpClientCredentials();
    subject.externalGroups("bogus", authentication);
  }

  @Test
  public void testGetAllGroups() throws Exception {
    setUpClientCredentials();
    when(externalGroupsService.getAllGroups()).thenReturn(singletonList(group()));

    List<Group> groups = subject.allGroups(authentication);
    assertEquals(1,groups.size());
  }

  @Test
  public void testMembers() throws Exception {
    setUpClientCredentials("members");
    when(externalGroupsService.getMembers("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon")).thenReturn(singletonList(MockProvider.MEMBER));

    List<Member> members = subject.members("urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon", authentication);
    assertEquals(1, members.size());
    assertEquals(MockProvider.MEMBER, members.get(0));
  }

  @Test
  public void testMembersIncExternal() throws Exception {
    setUpClientCredentials("members");
    when(externalGroupsService.getMembers("personId","urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon")).thenReturn(singletonList(MockProvider.MEMBER));

    List<Member> members = subject.membersIncExternalMembers("personId", "urn:collab:group:surfteams.nl:nl:surfnet:diensten:apachecon", authentication);
    assertEquals(1, members.size());
    assertEquals(MockProvider.MEMBER, members.get(0));
  }

  private Group group() {
    return new Group("id", "foo", "bar", "source", new Membership("membership"));
  }

  private void setUpClientCredentials(String scope) {
    when(authentication.getUserAuthentication()).thenReturn(new ClientCredentialsAuthentication("client_id", AuthorityUtils.createAuthorityList(scope)));
  }

  private void setUpClientCredentials() {
    setUpClientCredentials("groups");
  }

}
