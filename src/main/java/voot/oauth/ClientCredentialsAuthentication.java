package voot.oauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ClientCredentialsAuthentication extends AbstractAuthenticationToken {

  private String clientId;

  @SuppressWarnings("unchecked")
  public ClientCredentialsAuthentication(String clientId, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.clientId = clientId;
    super.setAuthenticated(true); // must use super, as we override
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
