package voot.oauth;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import voot.oauth.valueobject.Group;

public class ExternalGroupsQuerier {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsQuerier.class);

  private final List<GroupClient> groupClients;
  private final long maxExecutionTime;

  public ExternalGroupsQuerier(List<GroupClient> groupClients, Long maxExecutionTime) {
    Preconditions.checkArgument(groupClients.size() > 0, "No clients configured");

    long totalExecutionTime = groupClients.stream()
      .mapToLong(GroupClient::getMaxExecutionTime)
      .sum();
    Preconditions.checkArgument(totalExecutionTime > 0, "Clients must have a timeout set.");
    Preconditions.checkArgument(totalExecutionTime < maxExecutionTime, "Combined worst-case timeout (%s) ms exceeds configured maximum of %s ms", totalExecutionTime, maxExecutionTime);

    this.groupClients = groupClients;
    this.maxExecutionTime = maxExecutionTime;
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {
    // calling all downstream providers must not take any longer than their collective total timeoutsettings
    ForkJoinPool forkJoinPool = new ForkJoinPool(groupClients.size()); // we're I/O bound
    try {
      return forkJoinPool.submit(() -> this.groupClients.stream()
        .parallel()
        .filter(client -> client.isAuthorative(schacHomeOrganization))
        .map(client -> client.getMemberships(uid, schacHomeOrganization))
        .flatMap(Collection::stream)
        .collect(Collectors.toList())).get(maxExecutionTime + 100, TimeUnit.MILLISECONDS);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    } catch (TimeoutException e) {
      LOG.warn("One or more external group provider clients have exceeded their timeout setting.");
      return Collections.emptyList();
    }
  }
}
