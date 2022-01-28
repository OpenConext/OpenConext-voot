package voot.provider;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.AbstractTest;
import voot.model.Group;
import voot.model.Member;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;


class TeamsProviderClientTest extends AbstractTest {

    private TeamsProvider subject = new TeamsProviderClient(
            new Provider.Configuration(GroupProviderType.TEAMS, "http://localhost:8889",
                    new Provider.Configuration.Credentials("user", "password"),
                    2000, "test.surfteams.nl", "teams"));

    @Test
    public void isTeamsGroup() throws Exception {
        assertTrue(subject.isTeamsGroup("urn:collab:group:test.surfteams.nl:nl:surfnet:diensten:test-team"));
        assertFalse(subject.isTeamsGroup("urn:collab:group:example.org"));
        assertFalse(subject.isTeamsGroup(""));
    }

    @Test
    public void shouldBeQueriedForMemberships() throws Exception {
        assertTrue(subject.shouldBeQueriedForMemberships("whatever"));
    }

    @Test
    public void findByLocalGroupId() throws Exception {
        stubResponse("/group/nl:surfnet:diensten:test-team", "json/voot2/voot2_group.json");//"http://localhost:8971/user/test-team/groups"
        Optional<Group> groupOptional = subject.findByLocalGroupId("nl:surfnet:diensten:test-team");
        assertEquals("urn:collab:group:test.surfteams.nl:id3", groupOptional.get().id);
        assertEquals("teams", groupOptional.get().sourceID);
    }

    @Test
    public void findByLocalGroupId404() throws Exception {
        stubFor(get(urlEqualTo("/group/nl:surfnet:diensten:test-team"))
                .willReturn(aResponse().withStatus(404)));
        Optional<Group> groupOptional = subject.findByLocalGroupId("nl:surfnet:diensten:test-team");
        assertFalse(groupOptional.isPresent());
    }

    @Test
    public void linkedLocalTeamsGroup() throws Exception {
        stubResponse("/linked-locals", "json/voot2/voot2_groups.json");
        List<Group> groups = subject.linkedLocalTeamsGroup(Arrays.asList("urn:collab:group:avans:test-team", "urn:collab:group:avans:test-team-2"));
        assertEquals(2, groups.size());
    }

    @Test
    public void linkedExternalGroupIds() throws Exception {
        stubResponse("/linked-externals", "json/teams/external_team_ids.json");
        List<String> externalGroupIds = subject.linkedExternalGroupIds("urn:collab:group:avans:test-team");
        assertEquals(2, externalGroupIds.size());
    }

    @Test
    public void getAllGroups() throws Exception {
        stubResponse("/groups", "json/voot2/voot2_groups.json");
        List<Group> groups = subject.getAllGroups();
        assertEquals(2, groups.size());
    }

    @Test
    public void getMembers() throws Exception {
        stubResponse("/members/nl:surfnet:diensten:test-team", "json/teams/members.json");
        List<Member> members = subject.getMembers("nl:surfnet:diensten:test-team");
        assertEquals(2, members.size());
    }

    @Test
    public void getMembersWithMemberId() throws Exception {
        stubResponse("/members/nl:surfnet:diensten:test-team", "json/teams/members.json");
        List<Member> members = subject.getMembers("totally-ignored", "nl:surfnet:diensten:test-team");
        assertEquals(2, members.size());
    }

    private void stubResponse(String path, String fileName) throws IOException {
        InputStream inputStream = new ClassPathResource(fileName).getInputStream();
        String json = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        MappingBuilder mappingBuilder = get(urlPathEqualTo(path));
        stubFor(mappingBuilder.willReturn(
                aResponse().
                        withStatus(200).
                        withHeader("Content-type", "application/json").
                        withBody(json)
        ));
    }

}
