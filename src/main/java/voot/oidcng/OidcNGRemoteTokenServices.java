package voot.oidcng;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import voot.oidc.OidcRemoteTokenServices;

import java.util.Map;

public class OidcNGRemoteTokenServices extends OidcRemoteTokenServices {

  public OidcNGRemoteTokenServices(String checkTokenEndpointUrl, String clientId, String clientSecret, String issuer, String schacHomeOrganizationKey) {
    super(checkTokenEndpointUrl, clientId, clientSecret, issuer, schacHomeOrganizationKey);
  }

  @Override
  protected Map<String, Object> introspection(String accessToken) {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("token", accessToken);

    HttpEntity<Object> entity = new HttpEntity<>(body, getHttpHeaders());

    return getRestTemplate().exchange(getCheckTokenEndpointUrl(), HttpMethod.POST, entity, Map.class).getBody();
  }

  @Override
  protected HttpHeaders headersForIntrospection() {
    HttpHeaders headers = super.headersForIntrospection();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    return headers;
  }

}
