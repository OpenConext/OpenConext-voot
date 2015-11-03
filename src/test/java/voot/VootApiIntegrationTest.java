package voot;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VootServiceApplication.class)
@WebIntegrationTest(value = {"externalProviders.config.path=classpath:/testExternalProviders.yml", "oauth.checkToken.endpoint.url=http://localhost:12121/oauth/check_token"}, randomPort = true)
public class VootApiIntegrationTest {

  private static final Logger LOG = LoggerFactory.getLogger(VootApiIntegrationTest.class);

  private static final String SPECIFIC_MEMBERSHIP_URL_TEMPLATE = "/me/groups/%s";
  private static final String MEMBERSHIP_URL_TEMPLATE = "/user/%s/groups/%s";
  public static final Integer MOCK_AUTHORIZATION_SERVER_PORT = 12121;
  public static final Integer MOCK_VOOT_PROVIDER_PORT = 23232;
  private static final String TOKEN_VALUE = "TOKEN_VALUE";
  private static final String CLIENT_ID = "test_client_id";
  private static final String SCHAC_HOME = "surfteams.nl";
  private static final String LOCAL_UID = "admin";
  private static final String UID = "urn:collab:person:" + SCHAC_HOME + ":" + LOCAL_UID;

  @Rule
  public WireMockRule authorizationServerMock = new WireMockRule(wireMockConfig().port(MOCK_AUTHORIZATION_SERVER_PORT));

  @Rule
  public WireMockRule vootProviderMock = new WireMockRule(wireMockConfig().port(MOCK_VOOT_PROVIDER_PORT));

  @Value("${local.server.port}")
  int port;
  private HttpHeaders oauthHeaders;


  @Before
  public void before() throws Exception {
    client = new TestRestTemplate();

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

    oauthHeaders = new HttpHeaders();
    oauthHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    oauthHeaders.add("Authorization", "Bearer " + TOKEN_VALUE);

  }

  private TestRestTemplate client;

  @Test
  public void testSingleMembershipPositiveResult() {
    final String localGroupUrn = "nl:surfnet:diensten:apachecon";
    final String groupUrn = "urn:collab:group:" + SCHAC_HOME + ":" + localGroupUrn;
    final String url = "http://localhost:" + port + String.format(SPECIFIC_MEMBERSHIP_URL_TEMPLATE, groupUrn);
    // stub a response from the voot-provider that should be queried by the voot-implementation we are testing

    final String stubUrl = String.format(MEMBERSHIP_URL_TEMPLATE, LOCAL_UID, localGroupUrn);
    final String responseJson = "{\"foo\": \"bar\"}";
    LOG.debug("Stubbing response from a vootprovider at URL: {}", stubUrl);
    vootProviderMock.stubFor(get(urlMatching(stubUrl))
      //ensure the PreemptiveAuthenticationHttpComponentsClientHttpRequestFactory prevents an unnecessary call
      .withHeader("Authorization", equalTo("Basic " + Base64.encodeBase64String("foo:bar".getBytes())))
      .willReturn(aResponse()
          .withStatus(200)
          .withHeader("Content-type", "application/json")
        .withBody(responseJson)
    ));
    final ResponseEntity<String> entity = client.exchange(url, HttpMethod.GET, new HttpEntity<>(oauthHeaders), String.class);
    assertTrue("status must be 200 OK", HttpStatus.OK.equals(entity.getStatusCode()));
  }

  @Test
  public void testSingleMembershipIllegalGroupUrn() {
    final String illegalGroupUrn = "foo";
    final String url = "http://localhost:" + port + String.format(SPECIFIC_MEMBERSHIP_URL_TEMPLATE, illegalGroupUrn);
    final ResponseEntity<String> entity = client.exchange(url, HttpMethod.GET, new HttpEntity<>(oauthHeaders), String.class);
    // status must be 400 and error message meaningful

    assertTrue("status must be 400", HttpStatus.BAD_REQUEST.equals(entity.getStatusCode()));
    assertTrue("meaningful error message required", entity.getBody().contains("error"));
    assertTrue("not a valid", entity.getBody().contains(illegalGroupUrn));
    assertTrue("must report back the offending value", entity.getBody().contains(illegalGroupUrn));
  }

}
