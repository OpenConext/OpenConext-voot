package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.nimbusds.jose.util.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import voot.valueobject.Group;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class EduIDGuestProviderTest {

  private EduIDGuestProvider subject = new EduIDGuestProvider(
    new Provider.Configuration(GroupProviderType.GUESTS, "http://localhost:8971",
      new Provider.Configuration.Credentials("user", "password"),
      2000, "test.eduid.nl", "eduid"));

  @Rule
  public WireMockRule inviteServer = new WireMockRule(8971);


  @Test
  public void shouldBeQueriedForMemberships() {
  }

  @Test
  public void getGroupMemberships() throws IOException {
    String json = IOUtils.readInputStreamToString(new ClassPathResource("json/eduid/group_memberships.json").getInputStream());
    String urn = "urn:collab:person:example.com:admin";
    inviteServer.stubFor(get(urlPathEqualTo("/api/voot/" + urn))
      .willReturn(aResponse()
        .withStatus(200)
        .withHeader("Content-type", "application/json").
        withBody(json)));
    List<Group> groupMemberships = subject.getGroupMemberships(urn);
    assertEquals(2, groupMemberships.size());
  }

  @Test
  public void getAllGroups() {
    assertEquals(0, subject.getAllGroups().size());
  }

  @Test
  public void getGroupMembership() {
    assertFalse(subject.getGroupMembership("uid", "groupId").isPresent());

  }

  @Test
  public void getMembers() {
    assertEquals(0, subject.getMembers("groupId").size());
  }

  @Test
  public void getMembersByPersonId() {
    assertEquals(0, subject.getMembers("personId", "groupId").size());
  }
}
