package voot;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import voot.oauth.SchacHomeAuthentication;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private VootController subject;

  @Before
  public void before() {
    subject = new VootController(externalGroupsService);
  }

  @Mock
  private OAuth2Authentication auth;

  @Mock
  private SchacHomeAuthentication userAuthentication;

  @Mock
  private ExternalGroupsService externalGroupsService;

  @Test
  public void testEmptyResult() throws Exception {
    final String uid = "foo";
    when(auth.getName()).thenReturn(uid);
    when(auth.getUserAuthentication()).thenReturn(userAuthentication);
    final String schacHome = "surfnet.nl";
    when(userAuthentication.getSchacHomeAuthentication()).thenReturn(schacHome);
    when(externalGroupsService.getMyGroups(uid, schacHome)).thenReturn(Collections.emptyList());
    assertNotNull(subject.myGroups(auth));
  }
}
