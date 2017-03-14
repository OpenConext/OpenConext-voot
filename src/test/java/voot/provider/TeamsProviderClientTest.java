package voot.provider;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeamsProviderClientTest {

  private TeamsProvider subject = new TeamsProviderClient(
    new Provider.Configuration(GroupProviderType.TEAMS, "http://localhost:8971",
      new Provider.Configuration.Credentials("user", "password"),
      2000, "test.surfteams.nl", "teams"));

  @Rule
  public WireMockRule authorizationServerMock = new WireMockRule(8971);

  @Before
  public void before() {
    authorizationServerMock.resetAll();
  }

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
    assertEquals("urn:collab:group:test.surfteams.nl:id1", groupOptional.get().id);
    assertEquals("teams", groupOptional.get().sourceID);
  }

  @Test
  public void findByLocalGroupId404() throws Exception {
    authorizationServerMock.stubFor(get(urlEqualTo("/group/nl:surfnet:diensten:test-team"))
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
    authorizationServerMock.stubFor(mappingBuilder.willReturn(
      aResponse().
        withStatus(200).
        withHeader("Content-type", "application/json").
        withBody(json)
    ));
  }

}
