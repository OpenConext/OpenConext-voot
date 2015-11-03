package voot.oauth;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class CachedRemoteTokenServicesTest {

  @Test
  public void testLoadAuthentication() throws Exception {
    CachedRemoteTokenServices remoteTokenServices = new CachedRemoteTokenServices(200, 150);
    RestTemplate restTemplate = mock(RestTemplate.class);
    remoteTokenServices.setRestTemplate(restTemplate);

    Map<String, Object> map = new HashMap<>();
    map.put("client_id", "test");
    map.put("authorities", "admin");
    map.put("user_name", "admin");
    @SuppressWarnings("unchecked")
    ResponseEntity<Map> response = new ResponseEntity(map, HttpStatus.OK);

    when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class))).thenReturn(response);

    OAuth2Authentication authentication = remoteTokenServices.loadAuthentication("access_token");
    map.put("user_name", "someone_else");

    OAuth2Authentication cachedAuthentication = remoteTokenServices.loadAuthentication("access_token");
    // we expect to hit the cache
    assertEquals(authentication, cachedAuthentication);

    Thread.sleep(500);

    OAuth2Authentication newAuthentication = remoteTokenServices.loadAuthentication("access_token");

    //cache is cleaned
    assertNotEquals(authentication, newAuthentication);
  }
}
