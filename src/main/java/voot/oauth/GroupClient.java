package voot.oauth;

import java.util.List;

import voot.oauth.valueobject.Group;

public interface GroupClient {

  boolean isAuthorative(String schacHomeOrganization);

  List<Group> getMemberships(String uid, String schacHomeOrganization);

  Long getMaxExecutionTime();
}
