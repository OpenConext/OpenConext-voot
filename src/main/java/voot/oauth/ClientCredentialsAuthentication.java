package voot.oauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class ClientCredentialsAuthentication extends AbstractAuthenticationToken {

  private String clientId;

  @SuppressWarnings("unchecked")
  public ClientCredentialsAuthentication(String clientId) {
    super(Collections.EMPTY_LIST);
    this.clientId = clientId;
  }

  @Override
  public Object getCredentials() {
    return "";
  }

  @Override
  public Object getPrincipal() {
    return clientId;
  }
}
