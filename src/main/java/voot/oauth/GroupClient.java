package voot.oauth;

import java.util.List;

public interface GroupClient {

  boolean isAuthorative(String schacHomeOrganization);

  List<Voot2Group> getMemberships(String uid, String schacHomeOrganization);
}
