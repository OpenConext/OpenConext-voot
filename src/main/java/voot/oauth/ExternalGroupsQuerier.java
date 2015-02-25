package voot.oauth;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class ExternalGroupsQuerier {

  private final List<GroupClient> groupClients;
  private final Long timeoutMillis;

  public ExternalGroupsQuerier(List<GroupClient> groupClients, Long timeoutMillis) {
    this.groupClients = groupClients;
    this.timeoutMillis = timeoutMillis;
  }

  public List<Voot2Group> getMyGroups(String uid, String schacHomeOrganization) {

    ForkJoinPool forkJoinPool = new ForkJoinPool(groupClients.size());
    try {
      return forkJoinPool.submit(() -> this.groupClients.parallelStream()
        .filter(client -> client.isAuthorative(schacHomeOrganization))
        .map(client -> client.getMemberships(uid, schacHomeOrganization))
        .flatMap(lists -> lists.stream())
        .collect(Collectors.toList())
      ).get(timeoutMillis, TimeUnit.MILLISECONDS);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("hmm");
  }
}
