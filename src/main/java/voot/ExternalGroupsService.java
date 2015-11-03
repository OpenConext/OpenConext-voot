package voot;

import com.google.common.base.Preconditions;

import voot.provider.Provider;
import voot.valueobject.Group;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalGroupsService {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsService.class);

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

  public Optional<Group> getMyGroupById(String uid, String groupId) {
    try {
      List<Optional<Group>> optionals = forkJoinPool.submit(() -> this.providers.parallelStream()
        .filter(provider -> provider.shouldBeQueriedForGroup(groupId))
        .map(provider -> {
          try {
            return provider.getGroupMembership(uid, groupId);
          } catch (RuntimeException e) {
            LOG.warn("Provider {} threw exception: {} ", provider, e);
            return Optional.<Group> empty();
          }
        })
        .filter(Optional::isPresent)
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
          .map(provider -> {
            try {
              return provider.getGroupMemberships(uid);
            } catch (RuntimeException e) {
              LOG.warn("Provider {} threw exception: {} ", provider, e);
              return Collections.<Group>emptyList();
            }
          })
          .flatMap(Collection::stream)
          .collect(Collectors.toList());
      }).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

}
