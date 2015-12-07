package voot.oauth;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import voot.authz.AuthzSchacHomeAwareUserAuthenticationConverter;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractSchacHomeAwareUserAuthenticationConverterTest {

  private static ObjectMapper objectMapper = new ObjectMapper();

  @SuppressWarnings("unchecked")
  protected Map<String, ?> readJson(String jsonFile) throws IOException {
     return objectMapper.readValue(new ClassPathResource(jsonFile).getInputStream(),Map.class);
  }
}
