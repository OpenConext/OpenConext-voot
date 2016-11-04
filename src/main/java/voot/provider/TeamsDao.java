package voot.provider;

import voot.valueobject.Group;

import java.util.List;
import java.util.Optional;

public interface TeamsDao {

  List<String> linkedLocalGrouperGroupIds(String... fullyQualifiedExternalGroupIds) ;

  List<Group> linkedExternalGroups(String... grouperGroupId);

}
