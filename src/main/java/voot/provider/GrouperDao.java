package voot.provider;

import voot.valueobject.Group;

import java.util.List;

public interface GrouperDao {

  List<Group> groups(String subjectId);

  List<Group> groupsByName(String... groupNames);
}
