package voot.oauth;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;


public class SchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

  private static final String SCHAC_HOME_KEY = "schacHomeOrganization";

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    final Authentication authentication = super.extractAuthentication(authenticationAttributes);
    String schacHomeOrg = (String) authenticationAttributes.get(SCHAC_HOME_KEY);
    SchacHomeAuthentication schacHomeAuthentication = new DefaultSchacHomeAuthentication(schacHomeOrg,
      authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
    return schacHomeAuthentication;
  }
}
