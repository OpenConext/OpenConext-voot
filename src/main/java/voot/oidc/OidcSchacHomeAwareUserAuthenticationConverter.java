package voot.oidc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;


public class OidcSchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

  private static final String SCHAC_HOME_KEY = "schac_home";
  private static final String CLIENT_ID = "client_id";
  private static final String UNSPECIFIED_ID = "unspecified_id";

  private static final Set<GrantedAuthority> DEFAULT_AUTHORITIES = singleton(new SimpleGrantedAuthority("ROLE_USER"));

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    if (!authenticationAttributes.containsKey(UNSPECIFIED_ID) &&
      authenticationAttributes.containsKey(CLIENT_ID)) {
      return new ClientCredentialsAuthentication((String) authenticationAttributes.get(CLIENT_ID), DEFAULT_AUTHORITIES);
    } else if (authenticationAttributes.containsKey(UNSPECIFIED_ID) &&
      authenticationAttributes.containsKey(SCHAC_HOME_KEY)) {
      return new SchacHomeAuthentication((String) authenticationAttributes.get(SCHAC_HOME_KEY),
        authenticationAttributes.get(UNSPECIFIED_ID), "N/A", DEFAULT_AUTHORITIES);
    }
    throw new InvalidClientException(String.format("Unsupported client authentication. Must contain either %s or %s and %s",
      CLIENT_ID, UNSPECIFIED_ID, SCHAC_HOME_KEY));
  }

}
