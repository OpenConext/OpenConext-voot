package voot.oauth;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class OidcRemoteTokenServicesTest {

  private ResourceServerTokenServices subject = new OidcRemoteTokenServices("http://localhost:8889/introspect", "clientId", "secret");

  private String unspecifiedNameId = "urn:collab:person:example.com:admin";
  private String clientId = "https@//oidc.localhost.surfconext.nl";

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Test
  public void testLoadAuthenticationSuccess() throws Exception {
    OAuth2Authentication oAuth2Authentication = introspect("json/oidc/introspect.success.json");

    SchacHomeAuthentication authentication = (SchacHomeAuthentication) oAuth2Authentication.getUserAuthentication();
    assertPrincipal(unspecifiedNameId, authentication);
    assertEquals("surfteams.nl", authentication.getSchacHomeAuthentication());

    assertAuthentication(oAuth2Authentication, authentication);
  }

  @Test
  public void testLoadAuthenticationSuccessClientCredentials() throws Exception {
    OAuth2Authentication oAuth2Authentication = introspect("json/oidc/introspect.client_credentials.json");

    ClientCredentialsAuthentication authentication = (ClientCredentialsAuthentication) oAuth2Authentication.getUserAuthentication();
    assertPrincipal(clientId, authentication);

    assertAuthentication(oAuth2Authentication, authentication);
  }

  @Test(expected = InvalidTokenException.class)
  public void testLoadAuthenticationInactive() throws Exception {
    introspect("json/oidc/introspect.failure.json");
  }

  @Test(expected = InvalidTokenException.class)
  public void testLoadAuthenticationError() throws Exception {
    introspect("json/oidc/introspect.error.json");
  }

  private void assertAuthentication(OAuth2Authentication oAuth2Authentication, Authentication authentication) {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    assertEquals(1, authorities.size());
    assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());

    OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
    assertEquals(clientId, oAuth2Request.getClientId());
  }

  private void assertPrincipal(String unspecifiedNameId, Authentication authentication) {
    assertEquals(unspecifiedNameId, authentication.getName());
    assertEquals(unspecifiedNameId, authentication.getPrincipal());
  }

  private OAuth2Authentication introspect(String jsonFile) throws IOException {
    InputStream inputStream = new ClassPathResource(jsonFile).getInputStream();
    String response = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

    stubFor(get(urlPathEqualTo("/introspect")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));

    return subject.loadAuthentication("access-token");
  }
}
