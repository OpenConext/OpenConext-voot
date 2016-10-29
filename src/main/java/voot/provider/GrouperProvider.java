package voot.provider;

import voot.valueobject.Group;

import java.util.List;

public interface GrouperProvider extends Provider {

  boolean isGrouperGroup(String groupId);

  List<Group> getGroupMembershipsForLocalGroupId(final String... localGroupId);

  String getGroupIdPrefix();
}
