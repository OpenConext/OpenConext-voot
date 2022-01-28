package voot;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import voot.model.Group;
import voot.model.Member;
import voot.provider.Provider;
import voot.provider.TeamsProvider;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static voot.util.StreamUtils.optionalFromOptionalList;
import static voot.util.UrnUtils.extractLocalGroupId;

public class ExternalGroupsService {

    private static final Logger LOG = LoggerFactory.getLogger(ExternalGroupsService.class);

    private final List<Provider> providers;
    private final ForkJoinPool forkJoinPool;
    private final Optional<TeamsProvider> teamsProviderOptional;
    private final boolean supportLinkedGrouperExternalGroups;

    public ExternalGroupsService(List<Provider> providers, boolean supportLinkedGrouperExternalGroups) {
        Assert.isTrue(providers.size() > 0, "No clients configured");
        this.providers = providers;
        this.forkJoinPool = new ForkJoinPool(providers.size() * 20); // we're I/O bound.
        this.teamsProviderOptional = this.providers.stream().filter(provider -> provider instanceof TeamsProvider)
                .map(TeamsProvider.class::cast).findAny();
        this.supportLinkedGrouperExternalGroups = supportLinkedGrouperExternalGroups;
    }

    public Set<Group> getMyGroups(String uid, String schacHomeOrganization) {
        List<Group> groups = this.execute(
                provider -> provider.shouldBeQueriedForMemberships(schacHomeOrganization),
                provider -> provider.getGroupMemberships(uid),
                Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());

        if (supportLinkedGrouperExternalGroups && teamsProviderOptional.isPresent()) {
            TeamsProvider teamsProvider = teamsProviderOptional.get();
            List<String> externalGroupIds = groups.stream()
                    .filter(group -> !teamsProvider.isTeamsGroup(group.id))
                    .map(group -> group.id)
                    .collect(toList());
            if (!externalGroupIds.isEmpty()) {
                List<Group> linkedGroups = teamsProvider.linkedLocalTeamsGroup(externalGroupIds);
                groups.addAll(linkedGroups);
            }
        }

        return filterDuplicatesWithLowerImportance(groups);
    }

    public Set<Group> getMyExternalGroups(String uid, String schacHomeOrganization) {
        List<Group> groups = this.execute(
                provider -> provider.isExternalGroupProvider() && provider.shouldBeQueriedForMemberships(schacHomeOrganization),
                provider -> provider.getGroupMemberships(uid),
                Collections::<Group>emptyList).flatMap(Collection::stream).collect(toList());

        return filterDuplicatesWithLowerImportance(groups);
    }

    public Set<Member> getMembers(String groupId) {
        return this.execute(
                provider -> provider.shouldBeQueriedForMembers(groupId),
                provider -> provider.getMembers(groupId),
                Collections::<Member>emptyList).flatMap(Collection::stream).collect(toSet());
    }

    public Set<Member> getMembers(String personId, String groupId) {
        return this.execute(
                provider -> provider.shouldBeQueriedForMembers(groupId),
                provider -> provider.getMembers(personId, groupId),
                Collections::<Member>emptyList).flatMap(Collection::stream).collect(toSet());
    }

    public Optional<Group> getMyGroupById(String uid, String groupId) {
        return doGetMyGroupById(uid, groupId, true);
    }

    private Optional<Group> doGetMyGroupById(String uid, String groupId, boolean tryExternalLinkedGroups) {
        List<Optional<Group>> groups = this.execute(
                provider -> provider.shouldBeQueriedForGroup(groupId),
                provider -> provider.getGroupMembership(uid, groupId),
                Optional::<Group>empty).filter(Optional::isPresent).collect(toList());

        if (groups.isEmpty() && tryExternalLinkedGroups && supportLinkedGrouperExternalGroups && teamsProviderOptional.isPresent()) {
            TeamsProvider teamsProvider = this.teamsProviderOptional.get();

            if (teamsProvider.isTeamsGroup(groupId)) {
                String localGroupId = extractLocalGroupId(groupId);
                List<String> externalGroupIds = teamsProvider.linkedExternalGroupIds(localGroupId);
                List<Optional<Group>> externalGroups = externalGroupIds.stream()
                        .map(externalGroupId -> doGetMyGroupById(uid, externalGroupId, false))
                        .collect(toList());
                if (!externalGroups.isEmpty()) {
                    Optional<Group> group = teamsProvider.findByLocalGroupId(localGroupId);
                    groups.add(group);
                }
            }
        }
        return optionalFromOptionalList(groups);
    }

    public Set<Group> getAllGroups() {
        return this.execute(
                provider -> !provider.isExternalGroupProvider(),
                Provider::getAllGroups,
                Collections::<Group>emptyList).flatMap(Collection::stream).collect(toSet());
    }

    protected Set<Group> filterDuplicatesWithLowerImportance(List<Group> groups) {
        return groups.stream().collect(groupingBy(group -> group.id)).values().stream()
                .map(groupList -> groupList.stream().max(Comparator.comparing(group -> group.membership)).get()).collect(toSet());
    }

    @SneakyThrows
    private <T> Stream<T> execute(Predicate<Provider> providerFilter, ProviderCallback<T> callback, ExceptionProviderCallback<T> exceptionCallback) {
        return forkJoinPool.submit(() -> providers.parallelStream()
                .filter(providerFilter)
                .map(provider -> {
                    try {
                        return callback.execute(provider);
                    } catch (RuntimeException e) {
                        //broken window syndrome as some GroupProviders are poorly implemented
                        LOG.warn(String.format("Provider %s threw Exception", provider), e);
                        return exceptionCallback.result();
                    }
                })).get();
    }

}
