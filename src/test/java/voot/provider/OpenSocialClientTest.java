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
import static org.junit.Assert.*;

public class OpenSocialClientTest {

  private String admin = "urn:collab:person:example.org:admin";
  private static final String UID = "admin";
  private static final String GROUP_ID = "nl:surfnet:diensten:apachecon";
  private static final String USER_URN = "urn:collab:person:example.org:" + UID;
  private static final String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

  private Configuration configuration = new Configuration(GroupProviderType.OPEN_SOCIAL, "http://localhost:8889", new Configuration.Credentials("user", "password"), 2000, "example.org", "Example");
  private OpenSocialClient subject = new OpenSocialClient(configuration);

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  @Test
  public void testGetMemberships() throws Exception {
    stubCall("groups/" + UID, "json/opensocial/open_social_groups.json");
    final List<Group> memberships = subject.getGroupMemberships(USER_URN);

    assertGroups(memberships);
  }

  @Test
  public void testGetEmptyMemberships() throws Exception {
    stubCall("groups/" + UID, "json/opensocial/open_social_groups_empty.json");
    final List<Group> memberships = subject.getGroupMemberships(USER_URN);

    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetMembershipsDeprecatedCompoundId() throws Exception {
    stubCall("groups/admin", "json/opensocial/open_social_deprecated_groups.json");
    final List<Group> memberships = subject.getGroupMemberships(USER_URN);
    assertGroups(memberships);
  }

  @Test
  public void testGetSingleMembership() throws Exception {
    stubCall("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_groups_single.json");
    Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

    assertTrue(group.isPresent());
    assertAdminGroup(group.get());
  }

  @Test
  public void testGetSingleMembershipDeprecatedCompoundId() throws Exception {
    stubCall("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_deprecated_group.json");
    Optional<Group> group = subject.getGroupMembership(admin, GROUP_URN);

    assertTrue(group.isPresent());
    assertAdminGroup(group.get());
  }

  @Test
  public void testGetSingleMembershipEmpty() throws Exception {
    stubCall("groups/" + UID + "/" + GROUP_ID, "json/opensocial/open_social_groups_empty.json");
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
    assertEquals("member", group2.membership.basic);
  }

  private void assertAdminGroup(Group group1) {
    assertEquals("urn:collab:group:example.org:etc:sysadmingroup", group1.id);
    assertEquals("sysadmingroup", group1.displayName);
    assertEquals("Super users", group1.description);
    assertEquals("admin", group1.membership.basic);
  }

  private void stubCall(String queryPart, String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(get(urlEqualTo("/" + queryPart)).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));
  }

}
