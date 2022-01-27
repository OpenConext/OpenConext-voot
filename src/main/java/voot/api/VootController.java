package voot.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voot.AccessDeniedException;
import voot.ExternalGroupsService;
import voot.ResourceNotFoundException;
import voot.model.Group;
import voot.model.Member;
import voot.model.User;
import voot.util.UrnUtils;

import java.util.Optional;
import java.util.Set;

import static voot.util.UrnUtils.getSchacHomeFromPersonUrn;

@RestController
public class VootController {

    private static Logger LOG = LoggerFactory.getLogger(VootController.class);

    private ExternalGroupsService externalGroupsService;

    @Autowired
    public VootController(ExternalGroupsService externalGroupsService) {
        this.externalGroupsService = externalGroupsService;
    }

    @RequestMapping(value = "/me/groups")
    public Set<Group> myGroups(User user) {
        String schacHome = user.getSchacHomeOrganization();
        String clientId = user.getClientId();
        String unspecifiedId = user.getUnspecifiedId();

        LOG.debug("me/groups on behalf of uid: {}, schacHomeOrg: {}, clientId: {}", unspecifiedId, schacHome, clientId);

        Set<Group> myGroups = externalGroupsService.getMyGroups(unspecifiedId, schacHome);

        LOG.debug("me/groups result for uid {}: {}", user.getClass(), myGroups);
        return myGroups;
    }

    @RequestMapping(value = "/me/groups/{groupId:.+}")
    public Group specificGroupMembership(@PathVariable String groupId, User user) {
        String schacHome = user.getSchacHomeOrganization();
        String clientId = user.getClientId();
        String unspecifiedId = user.getUnspecifiedId();

        LOG.debug("groups/{} on behalf of uid {}, schacHomeOrg: {}, clientId {}", groupId, unspecifiedId, schacHome, clientId);

        if (!UrnUtils.isFullyQualifiedGroupName(groupId)) {
            throw new IllegalArgumentException(groupId);
        }
        Optional<Group> group = externalGroupsService.getMyGroupById(unspecifiedId, groupId);

        LOG.debug("groups/{} result for uid {}: {}", groupId, unspecifiedId, group);

        return group.orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/internal/groups/{userId:.+}/{groupId:.+}")
    public Group internalSpecificGroup(@PathVariable String userId, @PathVariable String groupId, User user) {
        String clientId = user.getClientId();

        LOG.debug("internal/groups/{}/{}, clientId {}", userId, groupId, clientId);

        assertClientCredentialsClient(user);

        Optional<Group> group = externalGroupsService.getMyGroupById(userId, groupId);

        LOG.debug("groups/{} result: {}", groupId, group);

        return group.orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/internal/groups/{userId:.+}")
    public Set<Group> internalGroups(@PathVariable String userId, User user) {
        String clientId = user.getClientId();

        LOG.debug("internal/groups/{}, clientId {}", userId, clientId);

        assertClientCredentialsClient(user);

        String schacHome = getSchacHomeFromPersonUrn(userId);
        Set<Group> myGroups = externalGroupsService.getMyGroups(userId, schacHome);

        LOG.debug("internal/groups/{} result: {}", userId, myGroups);

        return myGroups;
    }

    @RequestMapping(value = "/internal/external-groups/{userId:.+}")
    public Set<Group> externalGroups(@PathVariable String userId, User user) {
        String clientId = user.getClientId();

        LOG.debug("internal/external-groups/{}, clientId {}", userId, clientId);

        assertClientCredentialsClient(user);

        String schacHome = getSchacHomeFromPersonUrn(userId);
        Set<Group> groups = externalGroupsService.getMyExternalGroups(userId, schacHome);

        LOG.debug("internal/external-groups/{} result: {}", userId, groups);

        return groups;
    }

    @RequestMapping(value = "/internal/all-groups")
    public Set<Group> allGroups(User user) {
        String clientId = user.getClientId();

        LOG.debug("internal/all-groups, clientId {}", clientId);

        assertClientCredentialsClient(user);

        Set<Group> groups = externalGroupsService.getAllGroups();

        LOG.debug("internal/all-groupsresult: {}", groups.size());

        return groups;
    }

    @RequestMapping(value = "/members/{groupId:.+}")
    public Set<Member> members(@PathVariable String groupId, User user) {
        String clientId = user.getClientId();

        LOG.debug("members/{}, accessToken: {}, clientId {}", groupId, clientId);

        assertClientCredentialsClient(user);

        Set<Member> members = externalGroupsService.getMembers(groupId);

        LOG.debug("/members/{} result: {}", groupId, members);

        return members;
    }

    @RequestMapping(value = "/members/{personId:.+}/{groupId:.+}")
    public Set<Member> membersIncExternalMembers(@PathVariable String personId, @PathVariable String groupId, User user) {
        String clientId = user.getClientId();

        LOG.debug("members/{}, clientId {}", groupId, clientId);

        assertClientCredentialsClient(user);

        Set<Member> members = externalGroupsService.getMembers(personId, groupId);

        LOG.debug("/members/{}/{} result: {}", personId, groupId, members);

        return members;
    }

    private void assertClientCredentialsClient(User user) {
        if (StringUtils.hasText(user.getUnspecifiedId())) {
            throw new AccessDeniedException(String.format("ClientCredentials grant type required. Unspecified ID is %s",
                    user.getUnspecifiedId()));
        }
    }

}
