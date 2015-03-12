package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import voot.provider.AbstractProvider;
import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MockProvider extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(MockProvider.class);
  public static final String SCHAC_HOME_ORGANIZATION = "example.org";

  private final Long timeoutMillis;
  private final boolean simulateTimeout;

  public MockProvider(Long timeoutMillis, boolean simulateTimeout, GroupProviderType type) {
    super(new Provider.Configuration(type, "url", new Provider.Configuration.Credentials("user", "password"), 2000, SCHAC_HOME_ORGANIZATION));
    this.timeoutMillis = timeoutMillis;
    this.simulateTimeout = simulateTimeout;
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public boolean shouldBeQueriedForGroup(String schacHomeOrganization, String groupId) {
    return true;
  }

  @Override
  public List<Group> getGroupMemberships(String uid) {
    if (simulateTimeout) {
      try {
        Thread.sleep(timeoutMillis);
      } catch (InterruptedException e) {
      }
      LOG.debug("timed out");
      return Collections.emptyList();
    } else {
      LOG.debug("got result");
      return Arrays.asList(defaultGroup("id"));
    }
  }

  @Override
  public Optional<Group> getGroupMembership(String uid, String groupId) {
    return Optional.of(defaultGroup(groupId));
  }

  private Group defaultGroup(String id) {
    return new Group(id, "came from" + this.toString(), "description", Membership.defaultMembership);
  }
}
