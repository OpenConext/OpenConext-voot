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
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.*;

public abstract class AbstractRemoteTokenServicesTest {

  private DecisionResourceServerTokenServices subject = getRemoteTokenServices();

  protected static final int PORT = 8889;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(PORT);

  protected abstract DecisionResourceServerTokenServices getRemoteTokenServices();

  protected abstract void stubCallToAuthorisationEndpoint(String responseJson);

  protected abstract String getUnspecifiedNameId();
  protected abstract String getClientId();

  protected abstract String getSuccesCheckTokenJsonPath();
  protected abstract String getFailureCheckTokenJsonPath();
  protected abstract String getErrorCheckTokenJsonPath();
  protected abstract String getSuccesCheckTokenClientCredentialsJsonPath();

  @Test
  public void testLoadAuthenticationSuccess() throws Exception {
    OAuth2Authentication oAuth2Authentication = introspect(getSuccesCheckTokenJsonPath());

    SchacHomeAuthentication authentication = (SchacHomeAuthentication) oAuth2Authentication.getUserAuthentication();
    assertPrincipal(getUnspecifiedNameId(), authentication);
    assertEquals("surfteams.nl", authentication.getSchacHomeAuthentication());

    assertAuthentication(oAuth2Authentication, authentication);
  }

  @Test
  public void testLoadAuthenticationSuccessClientCredentials() throws Exception {
    OAuth2Authentication oAuth2Authentication = introspect(getSuccesCheckTokenClientCredentialsJsonPath());

    ClientCredentialsAuthentication authentication = (ClientCredentialsAuthentication) oAuth2Authentication.getUserAuthentication();
    assertPrincipal(getClientId(), authentication);

    assertAuthentication(oAuth2Authentication, authentication);
  }

  @Test(expected = InvalidTokenException.class)
  public void testLoadAuthenticationError() throws Exception {
    introspect(getErrorCheckTokenJsonPath());
  }

  public DecisionResourceServerTokenServices getSubject() {
    return subject;
  }

  private void assertAuthentication(OAuth2Authentication oAuth2Authentication, Authentication authentication) {
    assertTrue(oAuth2Authentication.isAuthenticated());

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    assertEquals(1, authorities.size());
    assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());

    OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
    assertEquals(getClientId(), oAuth2Request.getClientId());
  }

  private void assertPrincipal(String unspecifiedNameId, Authentication authentication) {
    assertEquals(unspecifiedNameId, authentication.getName());
    assertEquals(unspecifiedNameId, authentication.getPrincipal());
  }

  protected OAuth2Authentication introspect(String jsonFile) throws IOException {
    InputStream inputStream = new ClassPathResource(jsonFile).getInputStream();
    String response = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

    stubCallToAuthorisationEndpoint(response);

    return subject.loadAuthentication("access-token");
  }


}
