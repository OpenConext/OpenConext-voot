package voot.oauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class ClientCredentialsAuthentication extends AbstractAuthenticationToken {


  public ClientCredentialsAuthentication() {
    super(Collections.EMPTY_LIST);
  }

  @Override
  public Object getCredentials() {
    return "";
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
