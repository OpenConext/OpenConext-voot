package voot.authz;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.Assert;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;


public class AuthzSchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {


  private static final Set<GrantedAuthority> DEFAULT_AUTHORITIES = singleton(new SimpleGrantedAuthority("ROLE_USER"));

  private static final String SCHAC_HOME_KEY = "schacHomeOrganization";
  private static final String CLIENT_ID = "client_id";

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    Authentication authentication = super.extractAuthentication(authenticationAttributes);
    /*
     * Client credentials grant type does not contain an username
     */
    if (authentication == null) {
      Assert.isTrue(authenticationAttributes.containsKey(CLIENT_ID), String.format("Authentication (%s) does not contain %s attribute", authenticationAttributes, CLIENT_ID));
      return new ClientCredentialsAuthentication((String) authenticationAttributes.get(CLIENT_ID), DEFAULT_AUTHORITIES);
    } else {
      Assert.isTrue(authenticationAttributes.containsKey(SCHAC_HOME_KEY), String.format("Authentication (%s) does not contain %s attribute", authenticationAttributes, SCHAC_HOME_KEY));
      return new SchacHomeAuthentication((String) authenticationAttributes.get(SCHAC_HOME_KEY),
        authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
    }
  }
}
