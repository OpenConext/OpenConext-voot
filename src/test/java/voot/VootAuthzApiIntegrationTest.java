package voot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.test.WebIntegrationTest;

import java.io.IOException;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WebIntegrationTest(value = {"externalProviders.config.path=classpath:/testExternalProviders.yml", "authz.checkToken.endpoint.url=http://localhost:12121/oauth/check_token"}, randomPort = true)
public class VootAuthzApiIntegrationTest extends VootOidcApiIntegrationTest {

  private static final String CLIENT_ID = "test_client_id";
  private static final String SCHAC_HOME = "surfteams.nl";
  private static final String LOCAL_UID = "admin";
  private static final String UID = "urn:collab:person:" + SCHAC_HOME + ":" + LOCAL_UID;

  @Override
  protected void stubOAuthCheckToken() throws IOException {
    final ImmutableList<String> scopes = ImmutableList.of("read", "groups");
    final ImmutableMap<Object, Object> checkTokenAttrs = ImmutableMap.builder().
      put("authenticatingAuthority", "my-university").
      put("user_name", UID).
      put("scope", scopes).
      put("schacHomeOrganization", SCHAC_HOME).
      put("exp", 3600).
      put("authorities", ImmutableList.of("ROLE_USER")).
      put("goo", "noo").
      put("client_id", CLIENT_ID).build();

    final String json = new ObjectMapper().writeValueAsString(checkTokenAttrs);

    authorizationServerMock.stubFor(post(urlMatching("/oauth/check_token")).willReturn(
      aResponse().
        withStatus(200).
        withHeader("Content-type", "application/json").
        withBody(json)
    ));

  }
  @Override
  protected String getAccessToken() {
    return UUID.randomUUID().toString();
  }

}
