package voot.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
  @Qualifier("grouperDataSource")
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {
    subject = new GrouperDaoClient(new NamedParameterJdbcTemplate(dataSource), "grouper", PREFIX);

  }

  @Test
  public void testGroups() throws Exception {
    List<Group> groups = subject.groups("urn:collab:person:example.com:amin");
    assertEquals(10, groups.size());
  }

  @Test
  public void testFindGroupsByName() throws Exception {
    List<Group> groups = subject.groupsByName("nl:surfnet:diensten:bazenteam", "nl:surfnet:diensten:test123", "nl:surfnet:diensten:managementvo");
    assertEquals(3, groups.size());
  }

  @Test
  public void testFindGroupsByNameEmpty() throws Exception {
    List<Group> groups = subject.groupsByName();
    assertEquals(0, groups.size());
  }

}
