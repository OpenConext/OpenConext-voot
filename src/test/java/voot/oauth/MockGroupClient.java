package voot.oauth;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockGroupClient implements GroupClient {

  private static final Logger LOG = LoggerFactory.getLogger(MockGroupClient.class);
  @Override
  public boolean isAuthorative(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Voot2Group> getMemberships(String uid, String schacHomeOrganization) {
    try {
      LOG.debug("Sleeping");
      Thread.sleep(10);
    } catch (InterruptedException e) {}
    LOG.debug("Woke up");
    return Collections.emptyList();
  }
}
