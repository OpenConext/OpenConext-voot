package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import voot.valueobject.Group;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

public class Voot2ClientTest {

  private String admin = "urn:collab:person:example.org:admin";
  private Provider.Configuration configuration = new Provider.Configuration(GroupProviderType.VOOT2, "http://localhost:8889", new Provider.Configuration.Credentials("user", "password"), 2000, "example.org");
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
    stubCall("groups/admin", "json/voot2/voot2_groups.json");
    final List<Group> groups = subject.getGroupMemberships(admin);

    System.out.println(groups);
  }

  @Test
  public void testGetEmptyMemberships() throws Exception {
    stubCall("groups/admin", "json/voot2/voot2_groups_empty.json");
    final List<Group> memberships = subject.getGroupMemberships(admin);

    assertTrue(memberships.isEmpty());
  }

  @Test
  public void testGetSingleMembership() throws Exception {
    stubCall("groups/admin/groupId", "json/voot2/voot2_group.json");
    Optional<Group> group = subject.getGroupMembership(admin, "groupId");

    assertTrue(group.isPresent());
  }

  private void stubCall(String queryPart, String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(get(urlEqualTo("/" + queryPart)).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));
  }

}
