package voot.oidc;

import org.junit.Test;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import voot.oauth.AbstractRemoteTokenServicesTest;
import voot.oauth.DecisionResourceServerTokenServices;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertFalse;

public class OidcRemoteTokenServicesTest extends AbstractRemoteTokenServicesTest {

  @Override
  protected DecisionResourceServerTokenServices getRemoteTokenServices() {
    return new OidcRemoteTokenServices("http://localhost:" + PORT + "/introspect", "clientId", "secret", "https://oidc.test2.surfconext.nl", "schac_home");
  }

  @Override
  protected void stubCallToAuthorisationEndpoint(String responseJson) {
    stubFor(get(urlPathEqualTo("/introspect")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(responseJson)));
  }

  @Override
  protected String getUnspecifiedNameId() {
    return "urn:collab:person:example.com:admin";
  }

  @Override
  protected String getClientId() {
    return "https@//oidc.localhost.surfconext.nl";
  }

  @Override
  protected String getSuccesCheckTokenJsonPath() {
    return "json/oidc/introspect.success.json";
  }

  @Override
  protected String getFailureCheckTokenJsonPath() {
    return "json/oidc/introspect.failure.json";
  }

  @Override
  protected String getErrorCheckTokenJsonPath() {
    return "json/oidc/introspect.error.json";
  }

  @Override
  protected String getSuccesCheckTokenClientCredentialsJsonPath() {
    return "json/oidc/introspect.client_credentials.json";
  }

  @Test(expected = InvalidTokenException.class)
  public void testLoadAuthenticationFailure() throws Exception {
    introspect(getFailureCheckTokenJsonPath());
  }

  @Test
  public void testCanHandle() {
    range(0, 10).forEach(nbr -> assertFalse(getSubject().canHandle(UUID.randomUUID().toString())));
  }

  @Test
  public void testCanHandleJWT() {
    assertFalse(getSubject().canHandle("nope"));
  }
}
