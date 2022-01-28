package voot.provider;

import com.nimbusds.jose.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import voot.AbstractTest;
import voot.model.Group;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class EduIDGuestProviderTest extends AbstractTest {

    private EduIDGuestProvider subject = new EduIDGuestProvider(
            new Provider.Configuration(GroupProviderType.GUESTS, "http://localhost:8889",
                    new Provider.Configuration.Credentials("user", "password"),
                    2000, "test.eduid.nl", "eduid"));

    @Test
    void getGroupMemberships() throws IOException {
        String json = IOUtils.readInputStreamToString(new ClassPathResource("json/eduid/group_memberships.json").getInputStream());
        String urn = "urn:collab:person:example.com:admin";
        stubFor(get(urlPathEqualTo("/api/voot/" + urn))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json").
                        withBody(json)));
        List<Group> groupMemberships = subject.getGroupMemberships(urn);
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
