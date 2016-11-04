package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import voot.provider.GrouperProvider;
import voot.provider.Provider;
import voot.provider.TeamsDao;
import voot.util.UrnUtils;
import voot.valueobject.Group;
import voot.valueobject.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static voot.util.StreamUtils.optionalFromList;
import static voot.util.StreamUtils.optionalFromOptionalList;
import static voot.util.UrnUtils.extractLocalGroupId;

public class ExternalGroupsService {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsService.class);

  private final List<Provider> providers;
  private final ForkJoinPool forkJoinPool;
  private final Optional<GrouperProvider> grouperProvider;
  private final TeamsDao teamsDao;
  private final boolean supportLinkedGrouperExternalGroups;

  public ExternalGroupsService(List<Provider> providers, TeamsDao teamsDao, boolean supportLinkedGrouperExternalGroups) {
    Assert.isTrue(providers.size() > 0, "No clients configured");
    this.providers = providers;
    this.teamsDao = teamsDao;
    this.forkJoinPool = new ForkJoinPool(providers.size() * 20); // we're I/O bound.
    this.grouperProvider = this.providers.stream().filter(provider -> provider instanceof GrouperProvider)
      .map(GrouperProvider.class::cast).findAny();
    this.supportLinkedGrouperExternalGroups = supportLinkedGrouperExternalGroups;
  }

  public List<Group> getMyGroups(String uid, String schacHomeOrganization) {
    List<Group> groups = this.execute(
      provider -> provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      provider -> provider.getGroupMemberships(uid),
      Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());

    if (supportLinkedGrouperExternalGroups && grouperProvider.isPresent()) {
      GrouperProvider grouper = grouperProvider.get();
      String[] externalGroupIds = groups.stream()
        .filter(group -> !grouper.isGrouperGroup(group.id))
        .map(group -> group.id)
        .toArray(String[]::new);
      String[] grouperGroupIds = teamsDao.linkedLocalGrouperGroupIds(externalGroupIds).stream().toArray(String[]::new);
      List<Group> groupsFromGrouper = grouper.getGroupMembershipsForLocalGroupId(grouperGroupIds);
      groups.addAll(groupsFromGrouper);
    }

    return groups;
  }

  public List<Group> getMyExternalGroups(String uid, String schacHomeOrganization) {
    return this.execute(
      provider -> provider.isExternalGroupProvider() && provider.shouldBeQueriedForMemberships(schacHomeOrganization),
      provider -> provider.getGroupMemberships(uid),
      Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public List<Member> getMembers(String groupId) {
    return this.execute(
      provider -> provider.shouldBeQueriedForMembers(groupId),
      provider -> provider.getMembers(groupId),
      Collections::<Member>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public List<Member> getMembers(String personId, String groupId) {
    return this.execute(
      provider -> provider.shouldBeQueriedForMembers(groupId),
      provider -> provider.getMembers(personId, groupId),
      Collections::<Member>emptyList).flatMap(Collection::stream).collect(toList());
  }

  public Optional<Group> getMyGroupById(String uid, String groupId) {
    return doGetMyGroupById(uid, groupId, true);
  }

  private Optional<Group> doGetMyGroupById(String uid, String groupId, boolean tryExternalLinkedGroups) {
    List<Optional<Group>> groups = this.execute(
      provider -> provider.shouldBeQueriedForGroup(groupId),
      provider -> provider.getGroupMembership(uid, groupId),
      Optional::<Group>empty).filter(Optional::isPresent).collect(toList());

    if (groups.isEmpty() && tryExternalLinkedGroups && supportLinkedGrouperExternalGroups && grouperProvider.isPresent()) {
      GrouperProvider grouper = this.grouperProvider.get();

      if (grouper.isGrouperGroup(groupId)) {
        String localGroupId = extractLocalGroupId(groupId).get();
        List<Group> externalGroups = teamsDao.linkedExternalGroups(localGroupId);
        List<Optional<Group>> grouperGroups = externalGroups.stream()
          .map(externalGroup -> doGetMyGroupById(uid, externalGroup.id, false))
          .collect(toList());
        if (!grouperGroups.isEmpty()) {
          groups.add(findGroupByLocalGroupId(grouper, localGroupId));
        }
      }
    }
    return optionalFromOptionalList(groups);
  }

  private Optional<Group> findGroupByLocalGroupId(GrouperProvider grouper, String localGroupId) {
    List<Group> groupsFromGrouper = grouper.getGroupMembershipsForLocalGroupId(localGroupId);
    return optionalFromList(groupsFromGrouper);
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
