package voot.oauth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SchacHomeAuthentication extends UsernamePasswordAuthenticationToken {

  private final String schacHomeOrganization;

  public SchacHomeAuthentication(String schacHomeOrganization, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.schacHomeOrganization = schacHomeOrganization;
  }

  public String getSchacHomeAuthentication() {
    return schacHomeOrganization;
  }

}
