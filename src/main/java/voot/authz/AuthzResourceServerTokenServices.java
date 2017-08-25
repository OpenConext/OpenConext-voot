package voot.authz;

import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import voot.oauth.DecisionResourceServerTokenServices;

public class AuthzResourceServerTokenServices extends RemoteTokenServices implements DecisionResourceServerTokenServices {

  public AuthzResourceServerTokenServices(String authzCheckTokenClientId, String authzCheckTokenSecret, String authzCheckTokenEndpointUrl, AccessTokenConverter accessTokenConverter) {
    super();
    setCheckTokenEndpointUrl(authzCheckTokenEndpointUrl);
    setClientId(authzCheckTokenClientId);
    setClientSecret(authzCheckTokenSecret);

    setAccessTokenConverter(accessTokenConverter);

  }

  @Override
  public boolean canHandle(String accessToken) {
    //we only handle UUIDs
    return uuidPattern.matcher(accessToken).matches();
  }

}
