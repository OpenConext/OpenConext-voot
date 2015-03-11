package voot;

import com.google.common.base.Preconditions;
import voot.provider.Provider;
import voot.valueobject.Group;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class ExternalGroupsService {

  private final List<Provider> providers;
  private final ForkJoinPool forkJoinPool;


  public ExternalGroupsService(List<Provider> providers) {
    Preconditions.checkArgument(providers.size() > 0, "No clients configured");
    this.providers = providers;
    forkJoinPool = new ForkJoinPool(providers.size() * 20); // we're I/O bound.
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {
    try {
      return forkJoinPool.submit(() -> this.providers.parallelStream()
        .filter(provider -> provider.shouldBeQueriedFor(schacHomeOrganization))
        .map(provider -> provider.getGroupMemberships(uid))
        .flatMap(Collection::stream)
        .collect(Collectors.toList())).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

  public Optional<Group> getMyGroupById(String uid, String groupId, String schacHomeOrganization) {
    Optional<Provider> provider = this.providers.stream().filter(p -> p.getSchacHomeOrganization().equals(schacHomeOrganization)).findFirst();
    return provider.map(p -> p.getGroupMembership(uid, groupId)).get();
  }

}
