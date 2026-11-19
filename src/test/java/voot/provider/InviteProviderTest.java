package voot.provider;

import com.github.tomakehurst.wiremock.client.BasicCredentials;
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
            new Provider.Configuration(GroupProviderType.INVITE, "http://localhost:8889/api/external/v1/voot",
                    new Provider.Configuration.Credentials("user", "password"),
                    2000, "N/A", "invite", "userAgent"));

    @Test
    void getGroupMemberships() throws IOException {
        String json = IOUtils.readInputStreamToString(new ClassPathResource("json/invite/group_memberships.json").getInputStream());
        String urn = "urn:collab:person:example.com:admin";
        stubFor(get(urlPathEqualTo("/api/external/v1/voot/" + urn))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json").
                        withBody(json)));
        Set<Group> groupMemberships = subject.getGroupMemberships(urn);
        assertEquals(2, groupMemberships.size());
    }

    @Test
    void getGroupMembershipsMissingSlash() throws IOException {
        InviteProvider inviteProvider = new InviteProvider(
                new Provider.Configuration(GroupProviderType.INVITE, "http://localhost:8889/api/voot/",
                        new Provider.Configuration.Credentials("user", "password"),
                        2000, "N/A", "invite", "userAgent"));
        String json = IOUtils.readInputStreamToString(new ClassPathResource("json/invite/group_memberships.json").getInputStream());
        String urn = "urn:collab:person:example.com:admin";
        stubFor(get(urlPathEqualTo("/api/voot/" + urn))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json").
                        withBody(json)));
        Set<Group> groupMemberships = inviteProvider.getGroupMemberships(urn);
        assertEquals(2, groupMemberships.size());
    }

    @Test
    void getGroupMembershipsSendsCredentialsUpFront() throws IOException {
        String json = IOUtils.readInputStreamToString(new ClassPathResource("json/invite/group_memberships.json").getInputStream());
        String urn = "urn:collab:person:example.com:preemptive";
        String path = "/api/external/v1/voot/" + urn;
        stubFor(get(urlPathEqualTo(path)).willReturn(aResponse().withStatus(401)
                .withHeader("WWW-Authenticate", "Basic realm=\"test\"")));
        stubFor(get(urlPathEqualTo(path)).withBasicAuth("user", "password")
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-type", "application/json").withBody(json)));
        assertEquals(2, subject.getGroupMemberships(urn).size());
        verify(1, getRequestedFor(urlPathEqualTo(path)));
        verify(1, getRequestedFor(urlPathEqualTo(path)).withBasicAuth(new BasicCredentials("user", "password")));
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
