package voot.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CachingOpaqueTokenIntrospector extends SpringOpaqueTokenIntrospector {

    private static Log LOG = LogFactory.getLog(CachingOpaqueTokenIntrospector.class);

    private final Map<String, OAuth2AuthenticatedPrincipal> cache = new ConcurrentHashMap<>();
    private final boolean cacheTokens;

    public CachingOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret, boolean cacheTokens) {
        super(introspectionUri, clientId, clientSecret);
        this.cacheTokens = cacheTokens;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::cleanUp, 1L, 1L, TimeUnit.HOURS);
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        return cacheTokens ? cache.computeIfAbsent(token, s -> super.introspect(token)) : super.introspect(token);
    }

    void cleanUp() {
        Instant now = Instant.now();
        cache.values().removeIf(principal -> {
            boolean expired = ((Instant) principal.getAttribute("exp")).isBefore(now);
            if (expired) {
                String eppn = principal.getAttribute("eduperson_principal_name");
                LOG.info(String.format("Removing expired token for %s", eppn));
            }
            return expired;
        });
    }
}
