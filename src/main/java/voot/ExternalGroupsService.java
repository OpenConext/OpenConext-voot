package voot;

import com.google.common.base.Preconditions;
import voot.provider.AbstractProvider;
import voot.provider.Provider;
import voot.valueobject.Group;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExternalGroupsService {

  private final List<Provider> providers;
  private final ForkJoinPool forkJoinPool;


  public ExternalGroupsService(List<Provider> providers) {
    Preconditions.checkArgument(providers.size() > 0, "No clients configured");
    this.providers = providers;
    this.forkJoinPool = new ForkJoinPool(providers.size() * 20); // we're I/O bound.
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {
    Predicate<Provider> providerFilter = provider -> provider.shouldBeQueriedForMemberships(schacHomeOrganization);
    return doGetGroups(uid, providerFilter);
  }

  public List<Group> getMyExternalGroups(String uid, String schacHomeOrganization) {
    Predicate<Provider> providerFilter = provider -> provider.isExternalGroupProvider() && provider.shouldBeQueriedForMemberships(schacHomeOrganization);
    return doGetGroups(uid, providerFilter);
  }

  public Optional<Group> getMyGroupById(String uid, String groupId, String schacHomeOrganization) {
    try {
      List<Optional<Group>> optionals = forkJoinPool.submit(() -> this.providers.parallelStream()
        .filter(provider -> provider.shouldBeQueriedForGroup(schacHomeOrganization, groupId))
        .map(provider -> provider.getGroupMembership(uid, groupId))
        .filter(optionalGroup -> optionalGroup.isPresent())
        .collect(Collectors.toList())).get();
      return optionals.isEmpty() ? Optional.empty() : optionals.get(0);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

  private List<Group> doGetGroups(String uid, Predicate<Provider> providerFilter) {
    try {
      return forkJoinPool.submit(() -> {
        return this.providers.parallelStream()
          .filter(providerFilter)
          .map(provider -> provider.getGroupMemberships(uid))
          .flatMap(Collection::stream)
          .collect(Collectors.toList());
      }).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

}
