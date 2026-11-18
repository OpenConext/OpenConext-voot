package voot.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.util.StreamUtils;
import voot.AbstractTest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class CachingOpaqueTokenIntrospectorTest extends AbstractTest {

    private static final String URI = "http://localhost:8889/introspect";

    private final CachingOpaqueTokenIntrospector subject =
            new CachingOpaqueTokenIntrospector(URI, "rp", "secret", true);

    private void stubIntrospect(long exp) throws IOException {
        String json = StreamUtils.copyToString(new ClassPathResource("json/oidcng/introspect.success.json").getInputStream(), Charset.defaultCharset());
        json = String.format(json, "groups openid").replace("\"exp\": 1448436271", "\"exp\": " + exp);
        stubFor(post(urlPathMatching("/introspect")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(json)));
    }

    private void stubRevoked() {
        stubFor(post(urlPathMatching("/introspect")).willReturn(aResponse().withStatus(403)));
    }

    @Test
    void validTokenIsServedFromCache() throws IOException {
        stubIntrospect(Instant.now().plusSeconds(3600).getEpochSecond());
        subject.introspect("token");
        stubRevoked();
        Assertions.assertNotNull(subject.introspect("token"));
    }

    @Test
    void expiredTokenIsIntrospectedAgain() throws IOException {
        stubIntrospect(Instant.now().minusSeconds(10).getEpochSecond());
        subject.introspect("token");
        stubRevoked();
        Assertions.assertThrows(OAuth2IntrospectionException.class, () -> subject.introspect("token"));
    }

    @Test
    void cacheEntryExpiresAfterTtl() throws Exception {
        CachingOpaqueTokenIntrospector shortLived =
                new CachingOpaqueTokenIntrospector(URI, "rp", "secret", true, Duration.ofMillis(50), 10);
        stubIntrospect(Instant.now().plusSeconds(3600).getEpochSecond());
        shortLived.introspect("token");
        stubRevoked();
        Thread.sleep(100);
        //Revoked at the authorization server: must not be served from the cache after the TTL
        Assertions.assertThrows(OAuth2IntrospectionException.class, () -> shortLived.introspect("token"));
    }

    @Test
    void cacheSizeIsBounded() throws IOException {
        CachingOpaqueTokenIntrospector bounded =
                new CachingOpaqueTokenIntrospector(URI, "rp", "secret", true, Duration.ofMinutes(1), 1);
        stubIntrospect(Instant.now().plusSeconds(3600).getEpochSecond());
        bounded.introspect("one");
        bounded.introspect("two");
        stubRevoked();
        Assertions.assertNotNull(bounded.introspect("one"));
        //"two" did not fit in the cache, so it is introspected again
        Assertions.assertThrows(OAuth2IntrospectionException.class, () -> bounded.introspect("two"));
    }
}
