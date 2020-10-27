package voot.oauth;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.text.ParseException;
import java.util.Optional;

public interface DecisionResourceServerTokenServices extends ResourceServerTokenServices {

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
