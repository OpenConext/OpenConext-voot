package voot.oauth;

import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.regex.Pattern;

public interface DecisionResourceServerTokenServices extends ResourceServerTokenServices {

  Pattern uuidPattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

  boolean canHandle(String accessToken);

}
