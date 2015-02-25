package voot.oauth;

import java.util.List;
import java.util.stream.Collectors;

public class ExternalGroupsQuerier {

  private final List<GroupClient> groupClients;
  private final Long timeoutMillis;

  public ExternalGroupsQuerier(List<GroupClient> groupClients, Long timeoutMillis) {
    this.groupClients = groupClients;
    this.timeoutMillis = timeoutMillis;
  }

  public List<Voot2Group> getMyGroups(String uid, String schacHomeOrganization) {
    return this.groupClients.parallelStream()
      .filter(client -> client.isAuthorative(schacHomeOrganization))
      .map(client -> client.getMemberships(uid, schacHomeOrganization))
      .flatMap(lists -> lists.stream())
      .collect(Collectors.toList());
  }
}
