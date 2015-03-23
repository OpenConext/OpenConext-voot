package voot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import voot.oauth.SchacHomeAuthentication;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private VootController subject;
  private String uid = "foo";
  private String schacHome = "surfnet.nl";

  @Before
  public void before() {
    subject = new VootController(externalGroupsService);

    when(authentication.getName()).thenReturn(uid);
    when(authentication.getUserAuthentication()).thenReturn(userAuthentication);
    when(userAuthentication.getSchacHomeAuthentication()).thenReturn(schacHome);
    when(authentication.getDetails()).thenReturn(authDetails);
    when(authDetails.getTokenValue()).thenReturn("token_value");
    when(authentication.getOAuth2Request()).thenReturn(authRequest);
  }

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

  @Test
  public void testEmptyResult() throws Exception {
    when(externalGroupsService.getMyGroups(uid, schacHome)).thenReturn(Collections.emptyList());
    assertTrue(subject.myGroups(authentication).isEmpty());
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testResourceNotFound() throws Exception {
    String groupId = "urn:collab:group:" + schacHome + ":test:group";

    when(externalGroupsService.getMyGroupById(uid, groupId, schacHome)).thenReturn(Optional.empty());
    subject.specificGroup(groupId, authentication);
  }

  @Test(expected = AccessDeniedException.class)
  public void testAccessDeniedException() throws Exception {
    subject.internalSpecificGroup(uid, "", authentication);
  }
}
