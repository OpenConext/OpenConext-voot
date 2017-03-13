package voot.authz;

import org.junit.Test;
import voot.oauth.AbstractSchacHomeAwareUserAuthenticationConverterTest;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthzSchacHomeAwareUserAuthenticationConverterTest extends AbstractSchacHomeAwareUserAuthenticationConverterTest{

  private AuthzSchacHomeAwareUserAuthenticationConverter subject = new AuthzSchacHomeAwareUserAuthenticationConverter();

  @Test
  public void testExtractAuthenticationUserAuthentication() throws Exception {
    SchacHomeAuthentication authentication = (SchacHomeAuthentication) subject.extractAuthentication(readJson("json/authz/check_token.success.json"));
    assertTrue(authentication.isAuthenticated());
    assertEquals("urn:collab:person:surfteams.nl:admin", authentication.getName());
    assertEquals("surfteams.nl", authentication.getSchacHomeAuthentication());
  }

  @Test
  public void testExtractAuthenticationClientCredentialsAuthentication() throws Exception {
    ClientCredentialsAuthentication authentication = (ClientCredentialsAuthentication) subject.extractAuthentication(readJson("json/authz/check_token.client_credentials.json"));
    assertTrue(authentication.isAuthenticated());
    assertEquals("test_client_id", authentication.getName());
  }

}
