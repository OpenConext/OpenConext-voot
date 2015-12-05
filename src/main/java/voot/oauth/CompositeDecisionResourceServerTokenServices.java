package voot.oauth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;

public class CompositeDecisionResourceServerTokenServices implements DecisionResourceServerTokenServices {

  private final List<DecisionResourceServerTokenServices> tokenServices;

  public CompositeDecisionResourceServerTokenServices(List<DecisionResourceServerTokenServices> tokenServices) {
    this.tokenServices = tokenServices;
  }

  @Override
  public boolean canHandle(String accessToken) {
    for (DecisionResourceServerTokenServices tokenService : tokenServices) {
      if (tokenService.canHandle(accessToken)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
    for (DecisionResourceServerTokenServices tokenService : tokenServices) {
      if (tokenService.canHandle(accessToken)) {
        return tokenService.loadAuthentication(accessToken);
      }
    }
    throw new InvalidTokenException("Can not handle accessToken " + accessToken);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    for (DecisionResourceServerTokenServices tokenService : tokenServices) {
      if (tokenService.canHandle(accessToken)) {
        return tokenService.readAccessToken(accessToken);
      }
    }
    throw new InvalidTokenException("Can not handle accessToken " + accessToken);
  }
}
