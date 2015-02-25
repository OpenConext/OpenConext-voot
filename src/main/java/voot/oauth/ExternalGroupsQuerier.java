package voot.oauth;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import voot.oauth.valueobject.Group;

public class ExternalGroupsQuerier {

  private final List<GroupClient> groupClients;

  public ExternalGroupsQuerier(List<GroupClient> groupClients) {
    Preconditions.checkArgument(groupClients.size() > 0, "No clients configured");
    this.groupClients = groupClients;
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {
    // we're I/O bound and groupClients.size() will be < 5
    ForkJoinPool forkJoinPool = new ForkJoinPool(groupClients.size());
    try {
      return forkJoinPool.submit(() -> this.groupClients.parallelStream()
        .filter(client -> client.isAuthorative(schacHomeOrganization))
        .map(client -> client.getMemberships(uid, schacHomeOrganization))
        .flatMap(Collection::stream)
        .collect(Collectors.toList())).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }
}
