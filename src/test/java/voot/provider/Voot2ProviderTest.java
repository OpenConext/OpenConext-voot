package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.provider.Provider.Configuration;
import voot.valueobject.Group;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Voot2ProviderTest {

  private static final String UID = "admin";
  private static final String GROUP_ID = "nl:surfnet:diensten:apachecon";
  private static final String USER_URN = "urn:collab:person:example.org:" + UID;
  private static final String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

  private Configuration configuration = new Configuration(GroupProviderType.VOOT2, "http://localhost:8889",
    new Configuration.Credentials("user", "password"), 2000, "example.org", "example");

  private Voot2Provider subject = new Voot2Provider(configuration);

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Test
  public void testShouldBeQueriedForMemberships() throws Exception {
    assertTrue(subject.shouldBeQueriedForMemberships("example.org"));
    assertFalse(subject.shouldBeQueriedForMemberships("no.org"));
  }

  @Test
  public void testShouldBeQueriedForGroup() throws Exception {
    assertFalse(subject.shouldBeQueriedForGroup("no.urn"));
    assertFalse(subject.shouldBeQueriedForGroup("urn:collab:group:diffent.schac:group:name"));

    assertTrue(subject.shouldBeQueriedForGroup("urn:collab:group:" + configuration.schacHomeOrganization + ":group:name"));

  }

  @Test
  public void testGetMemberships() throws Exception {
    stubCall("user/" + UID + "/groups", "json/voot2/voot2_groups.json");
    List<Group> groups = subject.getGroupMemberships(USER_URN, true);
    assertTrue(groups.size() > 0);
  }

  @Test
  public void testGetEmptyMemberships() throws Exception {
    stubCall("user/" + UID + "/groups", "json/voot2/voot2_groups_empty.json");
    List<Group> memberships = subject.getGroupMemberships(USER_URN, true);

    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetEmptyMembershipsBecauseOfVootException() throws Exception {
    stubFor(get(urlEqualTo("/" + "user/" + UID + "/groups")).willReturn(aResponse().withStatus(304)));
    List<Group> memberships = subject.getGroupMemberships(USER_URN, true);
    assertTrue(memberships.isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetGroupMembershipsInvalidUid() {
    subject.getGroupMemberships("bogus", true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMembers() {
    subject.getMembers("bogus");
  }

  @Test
  public void testGetAllGroups() {
    assertEquals(0, subject.getAllGroups().size());
  }

  @Test
  public void testGetSpecificMembership() throws Exception {
    stubCall("user/" + UID + "/groups/" + GROUP_ID, "json/voot2/voot2_group.json");
    Optional<Group> group = subject.getGroupMembership(USER_URN, GROUP_URN);

    assertTrue(group.isPresent());
  }

  private void stubCall(String queryPart, String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(get(urlEqualTo("/" + queryPart)).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));
  }

}
