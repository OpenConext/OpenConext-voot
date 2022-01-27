package voot.model;

import lombok.Getter;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;

import java.util.Map;

@Getter
public class User {

    private String clientId;
    private String unspecifiedId;
    private String schacHomeOrganization;

    public User(BearerTokenAuthentication bearerTokenAuthentication) {
        Map<String, Object> tokenAttributes = bearerTokenAuthentication.getTokenAttributes();
        this.clientId = (String) tokenAttributes.get("client_id");
        this.unspecifiedId = (String) tokenAttributes.get("unspecified_id");
        this.schacHomeOrganization = (String) tokenAttributes.get("schac_home_organization");
    }

}
