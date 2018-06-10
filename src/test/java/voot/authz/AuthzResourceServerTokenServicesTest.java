package voot.authz;

import org.junit.Test;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import voot.oauth.AbstractRemoteTokenServicesTest;
import voot.oauth.DecisionResourceServerTokenServices;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertTrue;

public class AuthzResourceServerTokenServicesTest extends AbstractRemoteTokenServicesTest {

  @Override
  protected DecisionResourceServerTokenServices getRemoteTokenServices() {
    final DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    accessTokenConverter.setUserTokenConverter(new AuthzSchacHomeAwareUserAuthenticationConverter());
    return new AuthzResourceServerTokenServices("clientId","secret","http://localhost:" + PORT + "/check_token", accessTokenConverter);
  }

  @Override
  protected void stubCallToAuthorisationEndpoint(String responseJson) {
    stubFor(post(urlPathEqualTo("/check_token")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(responseJson)));
  }

  @Override
  protected String getUnspecifiedNameId() {
    return "urn:collab:person:surfteams.nl:admin";
  }

  @Override
  protected String getClientId() {
    return "test_client_id";
  }

  @Override
  protected String getSuccesCheckTokenJsonPath() {
    return "json/authz/check_token.success.json";
  }

  @Override
  protected String getFailureCheckTokenJsonPath() {
    return "json/authz/check_token.failure.json";
  }

  @Override
  protected String getErrorCheckTokenJsonPath() {
    return "json/authz/check_token.error.json";
  }

  @Override
  protected String getSuccesCheckTokenClientCredentialsJsonPath() {
    return "json/authz/check_token.client_credentials.json";
  }

  @Test(expected = InvalidTokenException.class)
  public void testLoadAuthenticationFailure() throws Exception {
    introspect(getFailureCheckTokenJsonPath());
  }

  @Test
  public void testCanHandle() {
    range(0, 10).forEach(nbr -> assertTrue(getSubject().canHandle(UUID.randomUUID().toString())));
  }
}
