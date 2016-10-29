package voot.provider;

import voot.valueobject.Group;

import java.util.List;
import java.util.Optional;

public interface TeamsDao {

  List<String> linkedGrouperGroupIds(String... externalGroupId) ;

  List<Group> linkedExternalGroups(String... grouperGroupId);

  Optional<Group> findExternalGroupById(String externalGroupId) ;
}
