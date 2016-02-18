package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import voot.provider.AbstractProvider;
import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Member;
import voot.valueobject.Membership;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MockProvider extends AbstractProvider {

  private static final Logger LOG = LoggerFactory.getLogger(MockProvider.class);
  public static final String SCHAC_HOME_ORGANIZATION = "example.org";
  public static final Member MEMBER = new Member("urn:collab:person:example.com:admin", "John Doe", "j.doe@example.com");

  public enum SimulationMode {Success, Timeout, Error }

  private final Long timeoutMillis;
  private final SimulationMode simulationMode;

  public MockProvider(Long timeoutMillis, SimulationMode simulationMode, GroupProviderType type) {
    super(new Provider.Configuration(type, "https://localhost/some/path", new Provider.Configuration.Credentials("user", "password"), 2000, SCHAC_HOME_ORGANIZATION, "example"));
    this.timeoutMillis = timeoutMillis;
    this.simulationMode = simulationMode;
  }

  @Override
  public boolean shouldBeQueriedForMemberships(String schacHomeOrganization) {
    return true;
  }

  @Override
  public boolean shouldBeQueriedForGroup(String groupId) {
    return true;
  }

  @Override
  public List<Group> getGroupMemberships(String uid) {
    return getResult(defaultGroup("id"));
  }

  @Override
  public Optional<Group> getGroupMembership(String uid, String groupId) {
    return Optional.of(defaultGroup(groupId));
  }

  @Override
  public List<Member> getMembers(String groupId) {
      return getResult(defaultMember());
  }

  private <T> List<T> getResult(T group) {
    switch (this.simulationMode) {
      case Timeout:
        try {
          Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
        }
        LOG.debug("timed out");
        return Collections.emptyList();
      case Error:
        throw new RuntimeException("failed!");
      default: // Success
        LOG.debug("got result");
        return Collections.singletonList(group);
    }
  }

  private Group defaultGroup(String id) {
    return new Group(id, "came from" + this.toString(), "description", SCHAC_HOME_ORGANIZATION, Membership.defaultMembership);
  }

  private Member defaultMember() {
    return MEMBER;
  }
}
