package voot.provider;

import voot.valueobject.Group;

import java.util.List;

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
