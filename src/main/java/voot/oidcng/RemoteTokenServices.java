package voot.oidcng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import voot.oauth.DecisionResourceServerTokenServices;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoteTokenServices implements DecisionResourceServerTokenServices {

  private static Logger LOG = LoggerFactory.getLogger(RemoteTokenServices.class);

  private String checkTokenEndpointUrl;
  private String clientId;
  private String clientSecret;
  private List<String> issuers;

  private AccessTokenConverter accessTokenConverter;

  private RestTemplate restTemplate;
  private HttpHeaders httpHeaders;

  public RemoteTokenServices(String checkTokenEndpointUrl, String clientId, String clientSecret, String issuer, String schacHomeOrganizationKey) {
    this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.issuers = Stream.of(issuer.split(",")).map(String::trim).collect(Collectors.toList());

    this.restTemplate = new RestTemplate();
    accessTokenConverter = new DefaultAccessTokenConverter();
    ((DefaultAccessTokenConverter) accessTokenConverter)
      .setUserTokenConverter(new SchacHomeAwareUserAuthenticationConverter(schacHomeOrganizationKey, "client_id", "unspecified_id"));
    this.httpHeaders = headersForIntrospection();
  }

  private Map<String, Object> introspection(String accessToken) {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("token", accessToken);

    HttpEntity<Object> entity = new HttpEntity<>(body, getHttpHeaders());

    ResponseEntity<Map> exchange = getRestTemplate().exchange(getCheckTokenEndpointUrl(), HttpMethod.POST, entity, Map.class);
    return exchange.getBody();
  }

  private HttpHeaders headersForIntrospection() {
    HttpHeaders headers = new HttpHeaders();
    String basicAuthz = clientId + ":" + clientSecret;
    String authenticationCredentials = "Basic " + new String(Base64.encode(basicAuthz.getBytes(Charset.forName("UTF-8"))));
    headers.add("Authorization", authenticationCredentials);
    headers.add("Accept", "application/json");
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }

  @Override
  @SuppressWarnings("unchecked")
  public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
    Map<String, Object> map;
    try {
      map = introspection(accessToken);
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
        throw new InvalidTokenException(accessToken);
      } else {
        throw e;
      }
    }

    if (map.containsKey("error")) {
      LOG.warn("introspect returned error: " + map.get("error"));
      throw new InvalidTokenException(accessToken);
    }
    if (!map.containsKey("active") || !(Boolean) map.get("active")) {
      LOG.warn("introspect returned inactive access_token: " + accessToken);
      throw new InvalidTokenException(accessToken);
    }

    Assert.state(map.containsKey("client_id"), "Client id must be present in response from auth server");
    //DefaultAccessTokenConverter#extractAuthentication expects the scope to be a Collection of Strings
    if (map.containsKey("scope")) {
      Object scope = map.get("scope");
      if (scope instanceof String) {
        map.put("scope", Arrays.asList(((String) scope).split(" ")));
      }
    }
    LOG.debug("Client {} has scopes {} for info {}", map.get("client_id"), map.get("scope"), map);
    return accessTokenConverter.extractAuthentication(map);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    return new DefaultOAuth2AccessToken(accessToken);
  }

  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public boolean canHandle(String accessToken) {
    return getIssuer(accessToken).map(iss -> this.issuers.contains(iss)).orElse(false);
  }

  public String getCheckTokenEndpointUrl() {
    return checkTokenEndpointUrl;
  }

  public MultiValueMap<String, String> getHttpHeaders() {
    return httpHeaders;
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
