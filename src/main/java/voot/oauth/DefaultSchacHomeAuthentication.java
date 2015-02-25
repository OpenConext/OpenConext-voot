package voot.oauth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class DefaultSchacHomeAuthentication extends UsernamePasswordAuthenticationToken implements SchacHomeAuthentication {

  private final String schacHomeOrganization;

  public DefaultSchacHomeAuthentication(String schacHomeOrganization, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.schacHomeOrganization = schacHomeOrganization;
  }

  @Override
  public String getSchacHomeAuthentication() {
    return schacHomeOrganization;
  }
}
