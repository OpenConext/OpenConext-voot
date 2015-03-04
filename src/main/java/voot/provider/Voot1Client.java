package voot.provider;

import java.util.List;

import voot.valueobject.Group;

public class Voot1Client implements Provider {

  private final Configuration configuration;

  public Voot1Client(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public boolean shouldBeQueriedFor(String schacHomeOrganization) {
    return false;
  }

  @Override
  public List<Group> getMemberships(String uid) {
    return null;
  }
}
