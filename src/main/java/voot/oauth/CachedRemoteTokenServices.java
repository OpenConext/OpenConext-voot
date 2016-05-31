package voot.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class CachedRemoteTokenServices implements DecisionResourceServerTokenServices {

  private static final Logger LOG = LoggerFactory.getLogger(CachedRemoteTokenServices.class);

  private final Map<String, CachedOAuth2Authentication> authentications = new ConcurrentHashMap<>();

  private final long duration;

  private final DecisionResourceServerTokenServices tokenServices;

  public CachedRemoteTokenServices(DecisionResourceServerTokenServices tokenServices, long durationMilliseconds, long expiryIntervalCheckMilliseconds) {
    this.tokenServices = tokenServices;
    Assert.isTrue(durationMilliseconds > 0 && durationMilliseconds < 1000 * 60 * 61);
    Assert.isTrue(expiryIntervalCheckMilliseconds > 0 && expiryIntervalCheckMilliseconds < 1000 * 60 * 61);
    this.duration = durationMilliseconds;
    newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::clearExpiredAuthentications, 0, expiryIntervalCheckMilliseconds, TimeUnit.MILLISECONDS);
  }

  @Override
  public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
    CachedOAuth2Authentication cachedAuthentication = authentications.get(accessToken);
    long now = System.currentTimeMillis();
    if (cachedAuthentication != null && cachedAuthentication.timestamp + duration > now) {
      LOG.debug("Returning OAuth2Authentication from cache {}", cachedAuthentication.authentication);
      return cachedAuthentication.authentication;
    }
    OAuth2Authentication oAuth2Authentication = tokenServices.loadAuthentication(accessToken);
    //will not happen, but just to ensure this does not cause memory problems
    int size = authentications.size();
    if (size < 10000) {
      LOG.debug("Putting OAuth2Authentication in cache {} current size: {}", oAuth2Authentication, size + 1);
      authentications.put(accessToken, new CachedOAuth2Authentication(now, oAuth2Authentication));
    }
    return oAuth2Authentication;
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    return tokenServices.readAccessToken(accessToken);
  }

  private void clearExpiredAuthentications() {
    try {
      long now = System.currentTimeMillis();
      authentications.forEach((accessToken, authentication) -> {
        if (authentication.timestamp + duration < now) {
          LOG.debug("Removing expired authentication with access token {}", accessToken);
          authentications.remove(accessToken);
        }
      });
    } catch (Throwable t) {
      LOG.error("Error in clearExpiredAuthentications", t);
      //we don't rethrow as this would stop the subsequent scheduled clearing
    }
  }

  @Override
  public boolean canHandle(String accessToken) {
    return tokenServices.canHandle(accessToken);
  }

  private class CachedOAuth2Authentication {
    long timestamp;
    OAuth2Authentication authentication;

    public CachedOAuth2Authentication(long timestamp, OAuth2Authentication authentication) {
      this.timestamp = timestamp;
      this.authentication = authentication;
    }
  }

}
