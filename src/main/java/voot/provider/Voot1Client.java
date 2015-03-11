package voot.provider;

import voot.valueobject.Group;

import java.util.List;
import java.util.Optional;

public class Voot1Client extends AbstractProvider {

  public Voot1Client(Configuration configuration) {
    super(configuration);
  }

  @Override
  public List<Group> getGroupMemberships(String uid) {
    return null;
  }

  @Override
  public Optional<Group> getGroupMembership(String uid, String groupId) {
    return null;
  }


}
