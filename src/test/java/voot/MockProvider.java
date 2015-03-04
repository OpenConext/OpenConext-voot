package voot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import voot.provider.Provider;
import voot.valueobject.Group;

public class MockProvider implements Provider {

  private static final Logger LOG = LoggerFactory.getLogger(MockProvider.class);

  private final Long timeoutMillis;
  private final boolean simulateTimeout;

  public MockProvider(Long timeoutMillis, boolean simulateTimeout) {
    this.timeoutMillis = timeoutMillis;
    this.simulateTimeout = simulateTimeout;
  }

  @Override
  public boolean shouldBeQueriedFor(String schacHomeOrganization) {
    return true;
  }

  @Override
  public List<Group> getMemberships(String uid) {
    if (simulateTimeout) {
      try {
        Thread.sleep(timeoutMillis);
      } catch (InterruptedException e) {
      }
      LOG.debug("timed out");
      return Collections.emptyList();
    } else {
      LOG.debug("got result");
      return Arrays.asList(new Group("came from" + this.toString(), "id"));
    }
  }
}
