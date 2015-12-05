package voot.oauth;

import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

public class AuthzResourceServerTokenServices extends RemoteTokenServices implements DecisionResourceServerTokenServices {

  @Override
  public boolean canHandle(String accessToken) {
    //we only handle UUIDs
    return uuidPattern.matcher(accessToken).matches();
  }

}
