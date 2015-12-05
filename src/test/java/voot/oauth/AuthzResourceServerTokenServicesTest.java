package voot.oauth;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AuthzResourceServerTokenServicesTest {

  private DecisionResourceServerTokenServices subject = new AuthzResourceServerTokenServices();

  @Test
  public void testCanHandle() {
    for (int i = 0; i < 100; i++) {
      assertTrue(subject.canHandle(UUID.randomUUID().toString()));
    }
  }
}
