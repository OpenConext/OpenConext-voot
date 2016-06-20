package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import voot.provider.Provider;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    return this.execute(
      provider -> provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      provider -> provider.getGroupMemberships(uid, includeMemberships),
      Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public List<Group> getMyExternalGroups(String uid, String schacHomeOrganization) {
    return this.execute(
      provider -> provider.isExternalGroupProvider() && provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      provider -> provider.getGroupMemberships(uid, true),
      Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public List<Member> getMembers(String groupId) {
    return this.execute(
      provider -> provider.shouldBeQueriedForMembers(groupId),
      provider -> provider.getMembers(groupId),
      Collections::<Member>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public Optional<Group> getMyGroupById(String uid, String groupId) {
    List<Optional<Group>> groups = this.execute(
      provider -> provider.shouldBeQueriedForGroup(groupId),
      provider -> provider.getGroupMembership(uid, groupId),
      Optional::<Group>empty).filter(Optional::isPresent).collect(toList());
    return groups.isEmpty() ? Optional.empty() : groups.get(0);
  }

  public List<Group> getAllGroups() {
    return this.execute(
      provider -> !provider.isExternalGroupProvider(),
      Provider::getAllGroups,
      Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());
  }

  private <T> Stream<T> execute(Predicate<Provider> providerFilter, ProviderCallback<T> callback, ExceptionProviderCallback<T> exceptionCallback) {
    try {
      return forkJoinPool.submit(() -> providers.parallelStream()
        .filter(providerFilter)
        .map(provider -> {
          try {
            return callback.execute(provider);
          } catch (RuntimeException e) {
            LOG.warn("Provider {} threw exception: {} ", provider, e);
            return exceptionCallback.result();
          }
        })).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("Unable to schedule querying of external group providers.", e);
    }
  }

}
