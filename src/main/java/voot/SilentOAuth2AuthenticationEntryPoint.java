package voot;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

public class SilentOAuth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response, Exception exception) {
        return response;
    }

}
