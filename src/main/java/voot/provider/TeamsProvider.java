package voot.provider;

import voot.valueobject.Group;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TeamsProvider extends Provider {

  boolean isTeamsGroup(String groupId);

  Optional<Group> findByLocalGroupId(String localGroupId);

  String getGroupIdPrefix();

  List<Group> linkedLocalTeamsGroup(Collection<String> fullyQualifiedExternalGroupIds) ;

  List<String> linkedExternalGroupIds(String... teamIds);

}
