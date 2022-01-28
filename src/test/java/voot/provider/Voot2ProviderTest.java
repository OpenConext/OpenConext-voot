package voot.provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import voot.AbstractTest;
import voot.model.Group;
import voot.provider.Provider.Configuration;

import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

class Voot2ProviderTest extends AbstractTest {

    private static final String UID = "admin";
    private static final String GROUP_ID = "nl:surfnet:diensten:apachecon";
    private static final String USER_URN = "urn:collab:person:example.org:" + UID;
    private static final String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

    private Configuration configuration = new Configuration(GroupProviderType.VOOT2, "http://localhost:8889",
            new Configuration.Credentials("user", "password"), 2000, "example.org", "example");

    private Voot2Provider subject = new Voot2Provider(configuration);

    @Test
    void testShouldBeQueriedForMemberships() throws Exception {
        assertTrue(subject.shouldBeQueriedForMemberships("example.org"));
        assertFalse(subject.shouldBeQueriedForMemberships("no.org"));
    }

    @Test
    void testShouldBeQueriedForGroup() throws Exception {
        assertFalse(subject.shouldBeQueriedForGroup("no.urn"));
        assertFalse(subject.shouldBeQueriedForGroup("urn:collab:group:diffent.schac:group:name"));

        assertTrue(subject.shouldBeQueriedForGroup("urn:collab:group:" + configuration.schacHomeOrganization + ":group:name"));

    }

    @Test
    void testGetMemberships() throws Exception {
        stubCallVoot2("user/" + UID + "/groups", "json/voot2/voot2_groups.json");
        List<Group> groups = subject.getGroupMemberships(USER_URN);
        assertTrue(groups.size() > 0);
    }

    @Test
    void testGetEmptyMemberships() throws Exception {
        stubCallVoot2("user/" + UID + "/groups", "json/voot2/voot2_groups_empty.json");
        List<Group> memberships = subject.getGroupMemberships(USER_URN);

        assertTrue(memberships.isEmpty());
    }

    @Test
    void testGetEmptyMembershipsBecauseOfVootException() throws Exception {
        stubFor(get(urlEqualTo("/" + "user/" + UID + "/groups")).willReturn(aResponse().withStatus(304)));
        List<Group> memberships = subject.getGroupMemberships(USER_URN);
        assertTrue(memberships.isEmpty());
    }

    @Test
    void testGetGroupMembershipsInvalidUid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> subject.getGroupMemberships("bogus"));
    }

    @Test
    void testGetMembers() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> subject.getMembers("bogus"));
    }

    @Test
    void testGetAllGroups() {
        assertEquals(0, subject.getAllGroups().size());
    }

    @Test
    void testGetSpecificMembership() throws Exception {
        stubCallVoot2("user/" + UID + "/groups/" + GROUP_ID, "json/voot2/voot2_group.json");
        Optional<Group> group = subject.getGroupMembership(USER_URN, GROUP_URN);

        assertTrue(group.isPresent());
    }

}
