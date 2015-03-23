package voot.oauth;

import com.google.common.base.Preconditions;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;


public class SchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

  private static final String SCHAC_HOME_KEY = "schacHomeOrganization";
  private static final String CLIENT_ID = "client_id";

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    Authentication authentication = super.extractAuthentication(authenticationAttributes);
    /*
     * Client credentials grant type does not contain an username
     */
    if (authentication == null) {
      Preconditions.checkArgument(authenticationAttributes.containsKey(CLIENT_ID), "Authentication (%s) does not contain %s attribute", authenticationAttributes, CLIENT_ID);
      return new ClientCredentialsAuthentication((String) authenticationAttributes.get(CLIENT_ID));
    } else {
      Preconditions.checkArgument(authenticationAttributes.containsKey(SCHAC_HOME_KEY), "Authentication (%s) does not contain %s attribute", authenticationAttributes, SCHAC_HOME_KEY);
      return new SchacHomeAuthentication((String) authenticationAttributes.get(SCHAC_HOME_KEY),
        authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
    }
  }


}
