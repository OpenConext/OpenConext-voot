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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private VootController subject;

  @Before
  public void before() {
    subject = new VootController(externalGroupsService);
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
    String uid = "foo";
    String schacHome = "surfnet.nl";

    when(authentication.getName()).thenReturn(uid);
    when(authentication.getUserAuthentication()).thenReturn(userAuthentication);
    when(userAuthentication.getSchacHomeAuthentication()).thenReturn(schacHome);
    when(authentication.getDetails()).thenReturn(authDetails);
    when(authDetails.getTokenValue()).thenReturn("token_value");
    when(authentication.getOAuth2Request()).thenReturn(authRequest);

    when(externalGroupsService.getMyGroups(uid, schacHome)).thenReturn(Collections.emptyList());
    assertNotNull(subject.myGroups(authentication));
  }
}
