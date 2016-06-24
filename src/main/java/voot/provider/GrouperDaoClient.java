package voot.provider;

import org.springframework.jdbc.core.JdbcTemplate;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

import static voot.valueobject.Membership.*;

public class GrouperDaoClient {

  private final JdbcTemplate jdbcTemplate;
  private final String sourceId;
  private final String groupIdPrefix;

  public GrouperDaoClient(JdbcTemplate jdbcTemplate, String sourceId, String groupIdPrefix) {
    this.jdbcTemplate = jdbcTemplate;
    this.sourceId = sourceId;
    this.groupIdPrefix = groupIdPrefix;
  }

  public List<Group> groups(String subjectId) {
    List<Group> groups = this.jdbcTemplate.query(
      "select gf.name as role, gg.name as groupname," +
        " gg.description as description, gg.display_extension as display_extension" +
        " from grouper_memberships gms, grouper_groups gg, grouper_fields gf, grouper_stems gs, grouper_members gm " +
        " where gms.field_id = gf.id and gms.owner_group_id = gg.id and gms.member_id = gm.id and gm.subject_id = ?" +
        " and gg.parent_stem = gs.id and gs.name != 'etc' and gm.subject_id = ?" +
        " and (gf.name = 'admins' or gf.name = 'updaters' or gf.name = 'members') order by gg.name",
      new Object[]{subjectId},
      (resultSet, i) ->
        new Group(groupIdPrefix + resultSet.getString("groupname"), resultSet.getString("groupname"),
          resultSet.getString("display_extension"), sourceId, membership(resultSet))
    );
    return groups.stream().collect(Collectors.groupingBy(group -> group.id)).values().stream()
      .map(this::mostImportant).collect(toList());
  }

  private Group mostImportant(List<Group> groups) {
    return groups.stream().max((o1, o2) -> {
      if (o1.equals(ADMIN)) {
        return 1;
      }
      if (o1.equals(MANAGER) && o2.equals(ADMIN)) {
        return -1;
      }
      return 0;
    }).get();
  }

  private Membership membership(ResultSet resultSet) throws SQLException {
    switch (resultSet.getString("role")) {
      case "updaters":
        return MANAGER;
      case "admins":
        return ADMIN;
      default:
        return MEMBER;
    }
  }
}
