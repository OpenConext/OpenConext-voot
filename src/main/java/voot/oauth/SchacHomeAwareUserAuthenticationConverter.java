package voot.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;


public class SchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

  private static final String SCHAC_HOME_KEY = "schacHomeOrganization";

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    Authentication authentication = super.extractAuthentication(authenticationAttributes);
    /*
     * Client credentials grant type does not contain an username
     */
    return authentication == null ? authentication : new SchacHomeAuthentication((String) authenticationAttributes.get(SCHAC_HOME_KEY),
      authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
  }

}
