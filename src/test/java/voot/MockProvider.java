package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import voot.model.Group;
import voot.model.Member;
import voot.model.Membership;
import voot.provider.AbstractProvider;
import voot.provider.GroupProviderType;
import voot.provider.Provider;
import voot.util.UrnUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MockProvider extends AbstractProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MockProvider.class);
    public static final String SCHAC_HOME_ORGANIZATION = "example.org";

    public enum SimulationMode {Success, Timeout, Error, Empty}

    private final Long timeoutMillis;
    private final SimulationMode simulationMode;

    public MockProvider(Long timeoutMillis, SimulationMode simulationMode, GroupProviderType type) {
        super(new Provider.Configuration(type, "https://localhost/some/path", new Provider.Configuration.Credentials("user", "password"), 2000, SCHAC_HOME_ORGANIZATION, "example"));
        this.timeoutMillis = timeoutMillis;
        this.simulationMode = simulationMode;
    }

    public MockProvider(SimulationMode simulationMode, GroupProviderType type, String schacHome) {
        super(new Provider.Configuration(type, "https://localhost/some/path", new Provider.Configuration.Credentials("user", "password"), 2000, schacHome, "example"));
        this.timeoutMillis = 0L;
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
    public boolean shouldBeQueriedForMembers(String groupId) {
        String localGroupId = UrnUtils.getSchacHomeFromGroupUrn(groupId);
        return localGroupId.equals(configuration.schacHomeOrganization);
    }

    @Override
    public Set<Group> getGroupMemberships(String uid) {
        return getResult(defaultGroup("id"));
    }

    @Override
    public Set<Group> getAllGroups() {
        return Collections.singleton(defaultGroup("id"));
    }


    @Override
    public Optional<Group> getGroupMembership(String uid, String groupId) {
        return Optional.of(defaultGroup(groupId));
    }

    @Override
    public Set<Member> getMembers(String groupId) {
        return getResult(defaultMember());
    }

    @Override
    public Set<Member> getMembers(String personId, String groupId) {
        return getResult(defaultMember());
    }

    private <T> Set<T> getResult(T result) {
        switch (this.simulationMode) {
            case Timeout:
                try {
                    Thread.sleep(timeoutMillis);
                } catch (InterruptedException e) {
                }
                LOG.debug("timed out");
                return Collections.emptySet();
            case Error:
                throw new RuntimeException("failed!");
            case Empty:
                return Collections.<T>emptySet();
            default: // Success
                LOG.debug("got result");
                return Collections.singleton(result);
        }
    }

    private Group defaultGroup(String id) {
        return new Group(UUID.randomUUID().toString() + id, "came from" + this.toString(), "description", SCHAC_HOME_ORGANIZATION, Membership.MEMBER);
    }

    private Member defaultMember() {
        return new Member("urn:collab:person:example.com:admin", "John Doe", "j.doe-" + UUID.randomUUID().toString() + "@example.com");
    }
}
