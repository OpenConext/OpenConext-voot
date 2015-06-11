package voot;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import voot.oauth.SchacHomeAuthentication;
import voot.valueobject.Group;
import voot.valueobject.Membership;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private static final String TOKEN_VALUE = "token_value";
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
    final List<Group> groups = subject.myGroups(authentication);
    assertTrue(groups.size() == 0);
  }

  @Test
  public void testSingleMembershipPositiveResult() {
    final Group group = new Group("id", "foo", "bar", "source", new Membership("membership"));
    when(externalGroupsService.getMyGroups(UID, SCHAC_HOME)).thenReturn(Collections.singletonList(group));
    final List<Group> groups = subject.myGroups(authentication);
    assertTrue(groups.size() > 0);
  }

  @Test(expected = VootController.MalformedGroupUrnException.class)
  public void testSingleMembershipIllegalGroupUrn() throws Exception {
    final String nonFullyQualifiedGroupId = "nl:surfnet:diensten:foo";
    subject.specificGroupMembership(nonFullyQualifiedGroupId, authentication);
  }


}
