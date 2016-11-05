package voot.provider;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import voot.valueobject.Group;
import voot.valueobject.Membership;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamsDaoClient implements TeamsDao {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public TeamsDaoClient(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<String> linkedLocalGrouperGroupIds(String... fullyQualifiedExternalGroupIds) {
    if (fullyQualifiedExternalGroupIds.length == 0) {
      return Collections.<String>emptyList();
    }
    return this.jdbcTemplate.query(
      "select distinct teg.grouper_team_id as grouper_team_id from team_external_groups teg, external_groups eg " +
        "where teg.external_groups_id = eg.id and eg.identifier in (:identifier) order by teg.grouper_team_id",
      new MapSqlParameterSource("identifier", Arrays.asList(fullyQualifiedExternalGroupIds)),
      (resultSet, i) -> resultSet.getString("grouper_team_id"));
  }

  @Override
  public List<Group> linkedExternalGroups(String... grouperGroupIds) {
    if (grouperGroupIds.length == 0) {
      return Collections.<Group>emptyList();
    }
    return this.jdbcTemplate.query("select eg.description as description, eg.group_provider as group_provider, " +
        "eg.identifier as identifier, eg.name as name from team_external_groups teg, external_groups eg " +
        "where teg.external_groups_id = eg.id and teg.grouper_team_id in (:grouper_team_ids) order by eg.identifier",
      new MapSqlParameterSource("grouper_team_ids", Arrays.asList(grouperGroupIds)),
      (resultSet, i) -> new Group(resultSet.getString("identifier"), resultSet.getString("name"),
        resultSet.getString("description"), resultSet.getString("group_provider"), Membership.MEMBER));
  }

}
