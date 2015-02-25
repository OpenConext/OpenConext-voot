package voot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
    subject = new VootController();
  }

  @Mock
  private OAuth2Authentication auth;

  @Mock
  private SchacHomeAuthentication userAuthentication;

  @Test
  public void testMyGroups() throws Exception {
    when(auth.getName()).thenReturn("foo");
    when(auth.getUserAuthentication()).thenReturn(userAuthentication);
    when(userAuthentication.getSchacHomeAuthentication()).thenReturn("surfnet.nl");

    assertNotNull(subject.myGroups(auth));
  }
}
