package voot.provider;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static voot.valueobject.Membership.*;

public class GrouperDaoClient implements GrouperDao {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final String sourceId;
  private final String groupIdPrefix;

  public GrouperDaoClient(NamedParameterJdbcTemplate jdbcTemplate, String sourceId, String groupIdPrefix) {
    this.jdbcTemplate = jdbcTemplate;
    this.sourceId = sourceId;
    this.groupIdPrefix = groupIdPrefix;
  }

  public List<Group> groups(String subjectId) {
    List<Group> groups = this.jdbcTemplate.query(
      "select gf.name as role, gg.name as groupname," +
        " gg.description as description, gg.display_extension as display_extension" +
        " from grouper_memberships gms, grouper_groups gg, grouper_fields gf, grouper_members gm " +
        " where gms.field_id = gf.id and gms.owner_group_id = gg.id and gms.member_id = gm.id and gm.subject_id = :subjectId" +
        " and (gf.name = 'admins' or gf.name = 'updaters' or gf.name = 'members') order by gg.name",
      new MapSqlParameterSource("subjectId", subjectId),
      (resultSet, i) ->
        new Group(groupIdPrefix + resultSet.getString("groupname"), resultSet.getString("display_extension"),
          resultSet.getString("description"), sourceId, membership(resultSet))
    );
    //the query returns duplicate groups because you can have multiple roles for one team
    Map<String, List<Group>> collect = groups.stream().collect(Collectors.groupingBy(group -> group.id));
    //we reduce the groups with the same ID to one group with the highest role
    return collect.values().stream().map(this::mostImportant).collect(toList());
  }

  @Override
  public List<Group> groupsByName(String... groupNames) {
    if (groupNames.length == 0) {
      return Collections.<Group>emptyList();
    }
    return this.jdbcTemplate.query("select gg.name as groupname, gg.description as description, gg.display_extension as display_extension " +
      "from grouper_groups gg where gg.name in (:names)",
      new MapSqlParameterSource("names", Arrays.asList(groupNames)),
      (resultSet, i) ->
        new Group(groupIdPrefix + resultSet.getString("groupname"), resultSet.getString("display_extension"),
          resultSet.getString("description"), sourceId, MEMBER));
  }

  private Group mostImportant(List<Group> groupList) {
    return groupList.stream().max((o1, o2) -> o1.membership.compareTo(o2.membership)).get();
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
