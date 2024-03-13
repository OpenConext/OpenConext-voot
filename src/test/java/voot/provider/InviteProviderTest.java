package voot.provider;

import com.nimbusds.jose.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import voot.AbstractTest;
import voot.model.Group;

import java.io.IOException;
import java.util.Set;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class InviteProviderTest extends AbstractTest {

    private final InviteProvider subject = new InviteProvider(
            new Provider.Configuration(GroupProviderType.INVITE, "http://localhost:8889",
                    new Provider.Configuration.Credentials("user", "password"),
                    2000, "N/A", "invite"));

    @Test
    void getGroupMemberships() throws IOException {
        String json = IOUtils.readInputStreamToString(new ClassPathResource("json/invite/group_memberships.json").getInputStream());
        String urn = "urn:collab:person:example.com:admin";
        stubFor(get(urlPathEqualTo("/api/voot/" + urn))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json").
                        withBody(json)));
        Set<Group> groupMemberships = subject.getGroupMemberships(urn);
        assertEquals(2, groupMemberships.size());
    }

    @Test
    void getAllGroups() {
        assertEquals(0, subject.getAllGroups().size());
    }

    @Test
    void getGroupMembership() {
        assertFalse(subject.getGroupMembership("uid", "groupId").isPresent());

    }

    @Test
    void getMembers() {
        assertEquals(0, subject.getMembers("groupId").size());
    }

    @Test
    void getMembersByPersonId() {
        assertEquals(0, subject.getMembers("personId", "groupId").size());
    }
}
