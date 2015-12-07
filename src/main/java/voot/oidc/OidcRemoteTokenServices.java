package voot.oidc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import voot.oauth.DecisionResourceServerTokenServices;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public class OidcRemoteTokenServices implements DecisionResourceServerTokenServices {

  private static Logger LOG = LoggerFactory.getLogger(OidcRemoteTokenServices.class);

  private String checkTokenEndpointUrl;
  private String clientId;
  private String clientSecret;

  private AccessTokenConverter accessTokenConverter;

  private RestTemplate restTemplate;

  public OidcRemoteTokenServices(String checkTokenEndpointUrl, String clientId, String clientSecret) {
    this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;

    this.restTemplate = new RestTemplate();
    accessTokenConverter = new DefaultAccessTokenConverter();
    ((DefaultAccessTokenConverter) accessTokenConverter).setUserTokenConverter(new OidcSchacHomeAwareUserAuthenticationConverter());

  }

  @Override
  public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
    String introspectUri = UriComponentsBuilder.fromHttpUrl(checkTokenEndpointUrl)
      .queryParam("token", accessToken)
      .build().toUriString();

    HttpEntity<Object> entity = new HttpEntity<>(headersForIntrospection());
    Map<String, Object> map = restTemplate.exchange(introspectUri, HttpMethod.GET, entity, Map.class).getBody();

    if (map.containsKey("error")) {
      LOG.warn("introspect returned error: " + map.get("error"));
      throw new InvalidTokenException(accessToken);
    }
    if (!map.containsKey("active") || !(Boolean)map.get("active")) {
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
    return accessTokenConverter.extractAuthentication(map);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    return new DefaultOAuth2AccessToken(accessToken);
  }

  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private MultiValueMap<String, String> headersForIntrospection() {
    HttpHeaders headers = new HttpHeaders();
    String basicAuthz = clientId + ":" + clientSecret;
    String authenticationCredentials = "Basic " + new String(Base64.encode(basicAuthz.getBytes(Charset.forName("UTF-8"))));
    headers.add("Authorization", authenticationCredentials);
    headers.add("Accept", "application/json");
    return headers;
  }

  @Override
  public boolean canHandle(String accessToken) {
    //we don't do UUIDs
    return !uuidPattern.matcher(accessToken).matches();
  }
}
