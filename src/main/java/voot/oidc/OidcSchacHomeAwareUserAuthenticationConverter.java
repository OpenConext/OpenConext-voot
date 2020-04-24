package voot.oidc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;


public class OidcSchacHomeAwareUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

  private String schacHomeKey;
  private String clientIdKey;
  private String unspecifiedIdKey;

  private static final Set<GrantedAuthority> DEFAULT_AUTHORITIES = singleton(new SimpleGrantedAuthority("ROLE_USER"));

  public OidcSchacHomeAwareUserAuthenticationConverter(String schacHomeKey, String clientIdKey, String unspecifiedIdKey) {
    this.schacHomeKey = schacHomeKey;
    this.clientIdKey = clientIdKey;
    this.unspecifiedIdKey = unspecifiedIdKey;
  }

  @Override
  public Authentication extractAuthentication(final Map<String, ?> authenticationAttributes) {
    if (!authenticationAttributes.containsKey(unspecifiedIdKey) &&
      authenticationAttributes.containsKey(clientIdKey)) {
      return new ClientCredentialsAuthentication((String) authenticationAttributes.get(clientIdKey), DEFAULT_AUTHORITIES);
    } else if (authenticationAttributes.containsKey(unspecifiedIdKey) &&
      authenticationAttributes.containsKey(schacHomeKey)) {
      return new SchacHomeAuthentication((String) authenticationAttributes.get(schacHomeKey),
        authenticationAttributes.get(unspecifiedIdKey), "N/A", DEFAULT_AUTHORITIES);
    }
    throw new InvalidClientException(String.format("Unsupported client authentication. Must contain either %s or %s and %s",
      clientIdKey, unspecifiedIdKey, schacHomeKey));
  }

}
