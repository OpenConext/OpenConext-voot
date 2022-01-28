package voot.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import voot.AbstractTest;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class CachingOpaqueTokenIntrospectorTest extends AbstractTest {

    private final CachingOpaqueTokenIntrospector subject =
            new CachingOpaqueTokenIntrospector("http://localhost:8889/introspect", "rp", "secret", true);

    @Test
    void introspect() throws IOException {
        String token = super.opaqueAccessToken("json/oidcng/introspect.success.json", "groups");
        subject.introspect(token);
        stubFor(post(urlPathMatching("/introspect")).willReturn(aResponse()
                .withStatus(403)));
        //Ensure the cache get hits
        subject.introspect(token);
        subject.cleanUp();
        Assertions.assertThrows(OAuth2IntrospectionException.class, () -> subject.introspect(token));
    }

}