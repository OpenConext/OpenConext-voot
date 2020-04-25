package voot.oidcng;

import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import voot.oauth.AbstractRemoteTokenServicesTest;
import voot.oauth.DecisionResourceServerTokenServices;
import voot.oidc.OidcRemoteTokenServicesTest;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static voot.JWTAccessToken.jwtAccessToken;

public class OidcNGRemoteTokenServicesTest extends OidcRemoteTokenServicesTest {

  @Override
  protected DecisionResourceServerTokenServices getRemoteTokenServices() {
    return new OidcNGRemoteTokenServices(
      "http://localhost:" + PORT + "/oidc/introspect",
      "clientId",
      "secret",
      "https://connect.test2.surfconext.nl , https://localhost.issuer ",
      "schac_home_organization");
  }

  @Override
  protected void stubCallToAuthorisationEndpoint(String responseJson) {
    stubFor(post(urlPathEqualTo("/oidc/introspect"))
      .withHeader(CONTENT_TYPE,
        new EqualToPattern(MediaType.APPLICATION_FORM_URLENCODED_VALUE, true))
      .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(responseJson)));
  }

  @Override
  protected String getSuccesCheckTokenJsonPath() {
    return "json/oidcng/introspect.success.json";
  }

  @Override
  protected String getSuccesCheckTokenClientCredentialsJsonPath() {
    return "json/oidcng/introspect.client_credentials.json";
  }

  @Test
  @Override
  public void testCanHandleJWT() {
    String accessToken = jwtAccessToken("https://localhost.issuer");
    assertTrue(getSubject().canHandle(accessToken));
  }
}
