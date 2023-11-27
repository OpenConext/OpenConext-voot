package voot.provider;

import voot.model.Group;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface TeamsProvider extends Provider {

    boolean isTeamsGroup(String groupId);

    Optional<Group> findByLocalGroupId(String localGroupId);

    Set<Group> linkedLocalTeamsGroup(Collection<String> fullyQualifiedExternalGroupIds);

    Set<String> linkedExternalGroupIds(String localGroupId);

}
