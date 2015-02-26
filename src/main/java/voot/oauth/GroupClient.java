package voot.oauth;

import java.util.List;

import voot.oauth.valueobject.Group;

public interface GroupClient {

  /**
   * Tells us if it is worthwhile calling this client
   * @param schacHomeOrganization the end-user's schacHomeOrg
   * @return
   */
  boolean shouldBeQueriedFor(String schacHomeOrganization);

  List<Group> getMemberships(String uid, String schacHomeOrganization);

}
