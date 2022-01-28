package voot.provider;

import voot.model.Group;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TeamsProvider extends Provider {

    boolean isTeamsGroup(String groupId);

    Optional<Group> findByLocalGroupId(String localGroupId);

    List<Group> linkedLocalTeamsGroup(Collection<String> fullyQualifiedExternalGroupIds);

    List<String> linkedExternalGroupIds(String localGroupId);

}
