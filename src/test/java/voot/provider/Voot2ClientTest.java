package voot.provider;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import voot.provider.Provider.Configuration;
import voot.valueobject.Group;

public class Voot2ClientTest {

  private static final String UID = "admin";
  private static final String GROUP_ID = "nl:surfnet:diensten:apachecon";
  private static final String USER_URN = "urn:collab:person:example.org:" + UID;
  private static final String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

  private Configuration configuration = new Configuration(GroupProviderType.VOOT2, "http://localhost:8889", new Configuration.Credentials("user", "password"), 2000, "example.org", "example");
  private Voot2Client subject = new Voot2Client(configuration);

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Test
  public void testShouldBeQueriedFor() throws Exception {
    assertTrue(subject.shouldBeQueriedForMemberships("example.org"));
    assertTrue(subject.shouldBeQueriedForGroup("example.org", "does.not.matter"));
    assertFalse(subject.shouldBeQueriedForGroup("bla.com", null));
  }

  @Test
  public void testGetMemberships() throws Exception {
    stubCall("groups/" + UID, "json/voot2/voot2_groups.json");
    final List<Group> groups = subject.getGroupMemberships(USER_URN);
    assertTrue(groups.size() > 0);
  }

  @Test
  public void testGetEmptyMemberships() throws Exception {
    stubCall("groups/" + UID, "json/voot2/voot2_groups_empty.json");
    final List<Group> memberships = subject.getGroupMemberships(USER_URN);

    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetSingleMembership() throws Exception {

    stubCall("groups/" + UID + "/" + GROUP_ID, "json/voot2/voot2_group.json");
    Optional<Group> group = subject.getGroupMembership(USER_URN, GROUP_URN);

    assertTrue(group.isPresent());
  }

  private void stubCall(String queryPart, String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(get(urlEqualTo("/" + queryPart)).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));
  }

}
