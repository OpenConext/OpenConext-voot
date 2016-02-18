package voot;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VootServiceApplication.class)
@WebIntegrationTest(value = {"externalProviders.config.path=classpath:/testExternalProviders.yml", "oidc.checkToken.endpoint.url=http://localhost:12121/introspect", "checkToken.cache=false"}, randomPort = true)
public class VootOidcApiIntegrationTest {

  private static final Logger LOG = LoggerFactory.getLogger(VootOidcApiIntegrationTest.class);
  private static final Integer MOCK_AUTHORIZATION_SERVER_PORT = 12121;
  private static final Integer MOCK_VOOT_PROVIDER_PORT = 23232;

  protected static final String SPECIFIC_MEMBERSHIP_URL_TEMPLATE = "/me/groups/%s";
  protected static final String MEMBERSHIP_URL_TEMPLATE = "/user/%s/groups/%s";

  protected static final String SCHAC_HOME = "surfteams.nl";
  protected static final String LOCAL_UID = "admin";

  protected TestRestTemplate client = new TestRestTemplate();

  @Rule
  public WireMockRule authorizationServerMock = new WireMockRule(MOCK_AUTHORIZATION_SERVER_PORT);

  @Rule
  public WireMockRule vootProviderMock = new WireMockRule(MOCK_VOOT_PROVIDER_PORT);

  @Value("${local.server.port}")
  int port;

  protected HttpHeaders oauthHeaders;


  @Before
  public void before() throws Exception {
    oauthHeaders = new HttpHeaders();
    oauthHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    oauthHeaders.add("Authorization", "Bearer " + getAccessToken());

    stubOAuthCheckTokenUser();

  }

  protected String getAccessToken() {
    return "TOKEN_VALUE";
  }

  protected void stubOAuthCheckTokenUser() throws IOException {
    doStubOAuthCheckToken("json/oidc/introspect.success.json");
  }

  protected void stubOAuthCheckTokenClientCredentials() throws IOException {
    doStubOAuthCheckToken("json/oidc/introspect.client_credentials.json");
  }

  protected void stubOAuthCheckTokenMissingScope() throws IOException {
    doStubOAuthCheckToken("json/oidc/introspect.missing_scope.json");
  }

  private void doStubOAuthCheckToken(String path) throws IOException {
    InputStream inputStream = new ClassPathResource(path).getInputStream();
    String json = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
    authorizationServerMock.stubFor(get(urlPathEqualTo("/introspect")).willReturn(
      aResponse().
        withStatus(200).
        withHeader("Content-type", "application/json").
        withBody(json)
    ));
  }

  @Test
  public void testSingleMembershipPositiveResult() {
    String localGroupUrn = "nl:surfnet:diensten:apachecon";
    String groupUrn = "urn:collab:group:" + SCHAC_HOME + ":" + localGroupUrn;
    String url = "http://localhost:" + port + String.format(SPECIFIC_MEMBERSHIP_URL_TEMPLATE, groupUrn);

    // stub a response from the voot-provider that should be queried by the voot-implementation we are testing
    String stubUrl = String.format(MEMBERSHIP_URL_TEMPLATE, LOCAL_UID, localGroupUrn);

    doExchange(url, stubUrl);
  }

  protected void doExchange(String url, String stubUrl) {
    // this is defined in the testExternalProviders.yml
    String responseJson = "{\"foo\": \"bar\"}";
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
  public void testWithClientCredentials() throws IOException {
    //override the stub for and see if we can do a call with ClientCredentials access token
    stubOAuthCheckTokenClientCredentials();

    String personUrn = "urn:collab:person:" + SCHAC_HOME + ":" + LOCAL_UID;
    String url = "http://localhost:" + port + String.format("/internal/groups/%s", personUrn);

    // stub a response from the voot-provider that should be queried by the voot-implementation we are testing
    String stubUrl = "/user/" + LOCAL_UID + "/groups";

    doExchange(url, stubUrl);
  }

  @Test
  public void testSingleMembershipIllegalGroupUrn() {
    String illegalGroupUrn = "foo";
    String url = "http://localhost:" + port + String.format(SPECIFIC_MEMBERSHIP_URL_TEMPLATE, illegalGroupUrn);
    ResponseEntity<String> entity = client.exchange(url, HttpMethod.GET, new HttpEntity<>(oauthHeaders), String.class);
    // status must be 400 and error message meaningful

    assertTrue("status must be 400", HttpStatus.BAD_REQUEST.equals(entity.getStatusCode()));
    assertTrue("meaningful error message required", entity.getBody().contains("error"));
    assertTrue("not a valid", entity.getBody().contains(illegalGroupUrn));
    assertTrue("must report back the offending value", entity.getBody().contains(illegalGroupUrn));
  }

  @Test
  public void testMissingScope() throws IOException {
    stubOAuthCheckTokenMissingScope();

    String url = "http://localhost:" + port + "/me/groups";
    ResponseEntity<String> entity = client.exchange(url, HttpMethod.GET, new HttpEntity<>(oauthHeaders), String.class);

    assertTrue(HttpStatus.FORBIDDEN.equals(entity.getStatusCode()));
    assertTrue(entity.getBody().contains("Insufficient scope for this resource"));
  }

  @Test
  public void testUnauthorizedAccess() throws IOException {
    String url = "http://localhost:" + port + "/me/groups";

    HttpHeaders plainHeaders = new HttpHeaders();
    plainHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

    ResponseEntity<String> entity = client.exchange(url, HttpMethod.GET, new HttpEntity<>(plainHeaders), String.class);

    assertTrue(HttpStatus.UNAUTHORIZED.equals(entity.getStatusCode()));
    assertTrue(entity.getBody().contains("Full authentication is required to access this resource"));
  }

}
