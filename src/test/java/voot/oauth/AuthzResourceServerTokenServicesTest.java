package voot.oauth;

import org.junit.Test;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.*;

public class AuthzResourceServerTokenServicesTest extends AbstractRemoteTokenServicesTest{

  @Override
  protected DecisionResourceServerTokenServices getRemoteTokenServices() {
    AuthzResourceServerTokenServices tokenServices = new AuthzResourceServerTokenServices();
    tokenServices.setCheckTokenEndpointUrl("http://localhost:" + PORT + "/check_token");
    tokenServices.setClientId("clientId");
    tokenServices.setClientSecret("secret");

    final DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    accessTokenConverter.setUserTokenConverter(new AuthzSchacHomeAwareUserAuthenticationConverter());
    tokenServices.setAccessTokenConverter(accessTokenConverter);
    return tokenServices;
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

  @Test(expected = IllegalStateException.class)
  public void testLoadAuthenticationFailure() throws Exception {
    introspect(getFailureCheckTokenJsonPath());
  }

  @Test
  public void testCanHandle() {
    range(0, 10).forEach(nbr -> assertTrue(getSubject().canHandle(UUID.randomUUID().toString())));
  }
}
