package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExternalGroupsService {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsService.class);

  private final List<Provider> providers;
  private final ForkJoinPool forkJoinPool;


  public ExternalGroupsService(List<Provider> providers) {
    Assert.isTrue(providers.size() > 0, "No clients configured");
    this.providers = providers;
    this.forkJoinPool = new ForkJoinPool(providers.size() * 20); // we're I/O bound.
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization, boolean includeMemberships) {
    return getGroupMemberships(
      uid,
      provider -> provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      includeMemberships);
  }

  public List<Group> getMyExternalGroups(String uid, String schacHomeOrganization) {
    return getGroupMemberships(
      uid,
      provider -> provider.isExternalGroupProvider() && provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      true);
  }

  public List<Member> getMembers(String groupId) {
    return doGet(groupId, provider -> provider.shouldBeQueriedForMembers(groupId), Provider::getMembers);
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
            return Optional.<Group>empty();
          }
        })
        .filter(Optional::isPresent)
        .collect(Collectors.toList())).get();
      return optionals.isEmpty() ? Optional.empty() : optionals.get(0);
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

  public List<Group> getGroupMemberships(String uid, Predicate<Provider> providerFilter, boolean includeMemberships) {
    try {
      return forkJoinPool.submit(() -> {
        return this.providers.parallelStream()
          .filter(providerFilter)
          .map(provider -> {
            try {
              return provider.getGroupMemberships(uid, includeMemberships);
            } catch (RuntimeException e) {
              LOG.warn("Provider {} threw exception: {} ", provider, e);
              return Collections.<Group>emptyList();
            }
          })
          .flatMap(Collection::stream)
          .collect(Collectors.toList());
      }).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of group memberships.", e);
    }
  }

  private <T> List<T> doGet(String argument, Predicate<Provider> providerFilter, BiFunction<Provider, String, List<T>> get) {
    try {
      return forkJoinPool.submit(() -> {
        return this.providers.parallelStream()
          .filter(providerFilter)
          .map(provider -> {
            try {
              return get.apply(provider, argument);
            } catch (RuntimeException e) {
              LOG.warn("Provider {} threw exception: {} ", provider, e);
              return Collections.<T>emptyList();
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
