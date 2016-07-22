package voot.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import voot.VootServiceApplication;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import javax.sql.DataSource;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VootServiceApplication.class)
@WebIntegrationTest(value = "flyway.enabled=true")
public class GrouperDaoClientTest {

  public static final String PREFIX = "urn:collab:group:grouper:";

  private GrouperDaoClient subject;

  @Autowired
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {
    subject = new GrouperDaoClient(new JdbcTemplate(dataSource), "grouper", PREFIX);

  }

  @Test
  public void testGroups() throws Exception {
    List<Group> groups = subject.groups("urn:collab:person:example.com:amin");
    assertEquals(5, groups.size());
    assertMembership(groups, PREFIX + "nl:surfnet:diensten:bazenteam", Membership.ADMIN);
    assertMembership(groups, PREFIX + "nl:surfnet:diensten:bassie_&_adriaan", Membership.ADMIN);
    assertMembership(groups, PREFIX + "nl:surfnet:diensten:burr", Membership.MANAGER);
    assertMembership(groups, PREFIX + "nl:surfnet:diensten:managementvo", Membership.MANAGER);
    assertMembership(groups, PREFIX + "nl:surfnet:diensten:test123", Membership.MEMBER);

    assertDisplayName(groups, PREFIX + "nl:surfnet:diensten:bassie_&_adriaan", "bassie & adriaan");
    assertDisplayName(groups, PREFIX + "nl:surfnet:diensten:bazenteam", "Bazenteam");
    assertDisplayName(groups, PREFIX + "nl:surfnet:diensten:burr", "burr");
    assertDisplayName(groups, PREFIX + "nl:surfnet:diensten:test123", "test123");

    assertDescription(groups, PREFIX + "nl:surfnet:diensten:bazenteam", "Eh");
    assertDescription(groups, PREFIX + "nl:surfnet:diensten:burr", "fffff");
    assertDescription(groups, PREFIX + "nl:surfnet:diensten:test123", "Testteam");

    assertSourceID(groups, PREFIX + "nl:surfnet:diensten:bazenteam", "grouper");
  }

  private void assertMembership(List<Group> groups, String groupId, Membership membership) {
    assertEquals(groupId, membership, groups.stream().filter(group -> group.id.equals(groupId)).collect(toList()).get(0).membership);
  }

  private void assertDisplayName(List<Group> groups, String groupId, String displayName) {
    assertEquals(groupId, displayName, groups.stream().filter(group -> group.id.equals(groupId)).collect(toList()).get(0).displayName);
  }

  private void assertDescription(List<Group> groups, String groupId, String description) {
    assertEquals(groupId, description, groups.stream().filter(group -> group.id.equals(groupId)).collect(toList()).get(0).description);
  }

  private void assertSourceID(List<Group> groups, String groupId, String sourceID) {
    assertEquals(groupId, sourceID, groups.stream().filter(group -> group.id.equals(groupId)).collect(toList()).get(0).sourceID);
  }
}
