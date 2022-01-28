package voot.provider;

import org.junit.jupiter.api.Test;
import voot.AbstractTest;
import voot.model.Group;
import voot.provider.Provider.Configuration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OpenSocialClientTest extends AbstractTest {

    private Configuration configuration = new Configuration(
            GroupProviderType.OPEN_SOCIAL,
            "http://localhost:8889",
            new Configuration.Credentials("user", "password"),
            2000,
            "example.org",
            "Example");

    private OpenSocialClient subject = new OpenSocialClient(configuration);

    @Test
    void testGetMemberships() throws Exception {
        stubCallVoot2("groups/" + UID, "json/opensocial/open_social_groups.json");
        final List<Group> memberships = subject.getGroupMemberships(USER_URN);

        assertGroups(memberships);
    }

    @Test
    void testGetEmptyMemberships() throws Exception {
        stubCallVoot2("groups/" + UID, "json/opensocial/open_social_groups_empty.json");
        final List<Group> memberships = subject.getGroupMemberships(USER_URN);

        assertTrue(memberships.isEmpty());
    }

    @Test
    void testGetMembershipsDeprecatedCompoundId() throws Exception {
        stubCallVoot2("groups/admin", "json/opensocial/open_social_deprecated_groups.json");
        final List<Group> memberships = subject.getGroupMemberships(USER_URN);
        assertGroups(memberships);
    }

    @Test
    void testGetSingleMembership() throws Exception {
        stubCallVoot2("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_groups_single.json");
        Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

        assertTrue(group.isPresent());
        assertAdminGroup(group.get());
    }

    @Test
    void testGetSingleMembershipDeprecatedCompoundId() throws Exception {
        stubCallVoot2("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_deprecated_group.json");
        Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

        assertTrue(group.isPresent());
        assertAdminGroup(group.get());
    }

    @Test
    void testGetSingleMembershipEmpty() throws Exception {
        stubCallVoot2("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_groups_empty.json");
        Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

        assertFalse(group.isPresent());
    }

    @Test
    void testGetMembershipWithoutEntry() throws Exception {
        stubCallVoot2("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_groups_no_results.json");
        Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

        assertFalse(group.isPresent());
    }

    private void assertGroups(List<Group> memberships) {
        assertEquals(2, memberships.size());

        Group group1 = memberships.get(0);
        assertAdminGroup(group1);

        Group group2 = memberships.get(1);
        assertEquals("urn:collab:group:example.org:nl:surfnet:diensten:test", group2.id);
        assertEquals("test title", group2.displayName);
        assertEquals("test description", group2.description);
        assertEquals("member", group2.membership.getBasic());
    }

    private void assertAdminGroup(Group group1) {
        assertEquals("urn:collab:group:example.org:etc:sysadmingroup", group1.id);
        assertEquals("sysadmingroup", group1.displayName);
        assertEquals("Super users", group1.description);
        assertEquals("admin", group1.membership.getBasic());
    }

}
