package voot.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class CachingOpaqueTokenIntrospector extends SpringOpaqueTokenIntrospector {

    private static final Log LOG = LogFactory.getLog(CachingOpaqueTokenIntrospector.class);

    static final long DEFAULT_TTL_SECONDS = 60L;
    static final int DEFAULT_MAX_ENTRIES = 10_000;

    private record CacheEntry(OAuth2AuthenticatedPrincipal principal, Instant cachedAt) {
    }

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final boolean cacheTokens;
    private final Duration ttl;
    private final int maxEntries;

    public CachingOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret, boolean cacheTokens) {
        this(introspectionUri, clientId, clientSecret, cacheTokens, Duration.ofSeconds(DEFAULT_TTL_SECONDS), DEFAULT_MAX_ENTRIES);
    }

    CachingOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret, boolean cacheTokens,
                                   Duration ttl, int maxEntries) {
        super(introspectionUri, clientId, clientSecret);
        this.cacheTokens = cacheTokens;
        this.ttl = ttl;
        this.maxEntries = maxEntries;
        if (cacheTokens) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread thread = new Thread(r, "introspection-cache-cleanup");
                thread.setDaemon(true);
                return thread;
            });
            executor.scheduleAtFixedRate(this::cleanUp, 1L, 1L, TimeUnit.MINUTES);
        }
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        if (!cacheTokens) {
            return super.introspect(token);
        }
        Instant now = Instant.now();
        CacheEntry entry = cache.get(token);
        if (entry != null && isValid(entry, now)) {
            return entry.principal();
        }
        //Not cached, stale or expired: always ask the authorization server, so revocation takes effect within the TTL
        OAuth2AuthenticatedPrincipal principal = super.introspect(token);
        if (cache.size() >= maxEntries) {
            cleanUp();
        }
        if (cache.size() < maxEntries) {
            cache.put(token, new CacheEntry(principal, now));
        } else {
            cache.remove(token);
        }
        return principal;
    }

    private boolean isValid(CacheEntry entry, Instant now) {
        if (entry.cachedAt().plus(ttl).isBefore(now)) {
            return false;
        }
        Object exp = entry.principal().getAttribute("exp");
        return !(exp instanceof Instant expiry) || !expiry.isBefore(now);
    }

    void cleanUp() {
        Instant now = Instant.now();
        cache.values().removeIf(entry -> {
            boolean invalid = !isValid(entry, now);
            if (invalid) {
                String eppn = entry.principal().getAttribute("eduperson_principal_name");
                LOG.debug(String.format("Removing stale cached token for %s", eppn));
            }
            return invalid;
        });
    }
}
