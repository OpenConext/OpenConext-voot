package voot.oauth;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import voot.oauth.valueobject.Group;

public class ExternalGroupsQuerier {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsQuerier.class);

  private final List<GroupClient> groupClients;

  public ExternalGroupsQuerier(List<GroupClient> groupClients) {
    this.groupClients = groupClients;
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {

    ForkJoinPool forkJoinPool = new ForkJoinPool(groupClients.size()); // we're I/O bound
    try {
      return forkJoinPool.submit(() -> this.groupClients.stream()
        .parallel()
        .filter(client -> client.isAuthorative(schacHomeOrganization))
        .map(client -> client.getMemberships(uid, schacHomeOrganization))
        .flatMap(lists -> lists.stream())
        .collect(Collectors.toList())).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }
}
