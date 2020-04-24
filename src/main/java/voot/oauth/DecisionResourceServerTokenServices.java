package voot.oauth;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.text.ParseException;
import java.util.Optional;
import java.util.regex.Pattern;

public interface DecisionResourceServerTokenServices extends ResourceServerTokenServices {

  Pattern uuidPattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

  default boolean isUUID(String accessToken) {
    return uuidPattern.matcher(accessToken).matches();
  }

  default Optional<String> getIssuer(String accessToken) {
    try {
      JWT jwt = JWTParser.parse(accessToken);
      return Optional.of(jwt.getJWTClaimsSet().getIssuer());
    } catch (ParseException e) {
      return Optional.empty();
    }
  }

  boolean canHandle(String accessToken);

}
