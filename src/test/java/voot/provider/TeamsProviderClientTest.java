package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
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
    stubResponse("/group/nl:surfnet:diensten:test-team","json/voot2/voot2_group.json");//"http://localhost:8971/user/test-team/groups"
    Optional<Group> groupOptional = subject.findByLocalGroupId("nl:surfnet:diensten:test-team");
    assertEquals("urn:collab:group:test.surfteams.nl:id1", groupOptional.get().id);
    assertEquals("teams", groupOptional.get().sourceID);
  }

  @Test
  public void findByLocalGroupId404() throws Exception {
    authorizationServerMock.stubFor(get(urlEqualTo("/group/nl:surfnet:diensten:test-team")).willReturn(
      aResponse().withStatus(404)));
    Optional<Group> groupOptional = subject.findByLocalGroupId("nl:surfnet:diensten:test-team");
    assertFalse(groupOptional.isPresent());
  }

  @Test
  public void linkedLocalTeamsGroup() throws Exception {

  }

  @Test
  public void linkedExternalGroupIds() throws Exception {

  }

  @Test
  public void getAllGroups() throws Exception {

  }

  @Test
  public void getMembers() throws Exception {

  }

  @Test
  public void getMembersWithMemberId() throws Exception {

  }

  private void stubResponse(String path, String fileName) throws IOException {
    InputStream inputStream = new ClassPathResource(fileName).getInputStream();
    String json = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    authorizationServerMock.stubFor(get(urlEqualTo(path)).willReturn(
      aResponse().
        withStatus(200).
        withHeader("Content-type", "application/json").
        withBody(json)
    ));
  }


}
