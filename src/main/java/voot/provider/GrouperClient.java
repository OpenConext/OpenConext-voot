package voot.provider;

import java.util.List;

import voot.valueobject.Group;

public class GrouperClient implements Provider {

  private final Configuration configuration;

  public GrouperClient(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public boolean shouldBeQueriedFor(String schacHomeOrganization) {
    return false;
  }

  @Override
  public List<Group> getMemberships(String uid, String schacHomeOrganization) {
    return null;
  }
}
