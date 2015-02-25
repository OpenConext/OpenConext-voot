package voot.oauth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import voot.oauth.valueobject.Group;

public class MockGroupClient implements GroupClient {

  private static final Logger LOG = LoggerFactory.getLogger(MockGroupClient.class);

  private final Long timeoutMillis;
  private final boolean simulateTimeout;

  public MockGroupClient(Long timeoutMillis, boolean simulateTimeout) {
    this.timeoutMillis = timeoutMillis;
    this.simulateTimeout = simulateTimeout;
  }

  @Override
  public boolean isAuthorative(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Group> getMemberships(String uid, String schacHomeOrganization) {
    if (simulateTimeout) {
      try {
        Thread.sleep(timeoutMillis);
      } catch (InterruptedException e) {}
      LOG.debug("timed out");
      return Collections.emptyList();
    } else {
      LOG.debug("got result");
      return Arrays.asList(new Group("came from" + this.toString()));
    }
  }

  @Override
  public Long getMaxExecutionTime() {
    return timeoutMillis;
  }
}
