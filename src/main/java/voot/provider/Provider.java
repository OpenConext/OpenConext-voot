package voot.provider;

import voot.model.Group;
import voot.model.Member;

import java.util.Optional;
import java.util.Set;

public interface Provider {

    /**
     * Tells us if it is worthwhile calling this client when returning all groups for an user
     *
     * @param schacHomeOrganization the end-user's schacHomeOrg
     * @return true if this Provider can return groups for the specified schacHomeOrganization
     */
    boolean shouldBeQueriedForMemberships(String schacHomeOrganization);

    /**
     * Tells us if it is worthwhile calling this client when returning a single specified group for an user
     *
     * @param groupId the group being requested (must be fully qualified persistent name or the unqualified Institution name)
     * @return true if this Provider can return groups for the specified schacHomeOrganization
     */
    boolean shouldBeQueriedForGroup(String groupId);

    /**
     * @return true if this Provider is external (e.g. not Grouper)
     */
    boolean isExternalGroupProvider();

    /**
     * @param uid the fully qualified uid
     */
    Set<Group> getGroupMemberships(String uid);

    /**
     * All groups
     */
    Set<Group> getAllGroups();

    /**
     * @param uid     the fully qualified uid
     * @param groupId the fully qualified uid groupId
     * @return the Group membership info if the user is indeed a member of the group, the empty Optional otherwise.
     */
    Optional<Group> getGroupMembership(String uid, String groupId);

    /**
     * Get all members of a group
     *
     * @param groupId the fully qualified uid groupId
     * @return all Members
     */
    Set<Member> getMembers(String groupId);

    /**
     * Get all members of a group
     *
     * @param personId the non-fully qualified personId
     * @param groupId  the fully qualified uid groupId
     * @return all Members
     */
    Set<Member> getMembers(String personId, String groupId);

    /**
     * Tells us if it is worthwhile calling this client when returning all members of a group
     *
     * @param groupId the fully qualified uid groupId
     * @return true if this Provider can return memers for the specified group id
     */
    boolean shouldBeQueriedForMembers(String groupId);

    class Configuration {

        public final GroupProviderType type;
        public final String url;
        public final Credentials credentials;
        public final Integer timeOutMillis;
        public final String schacHomeOrganization;
        public final String name;

        public Configuration(GroupProviderType type, String url, Credentials credentials, Integer timeOutMillis, String schacHomeOrganization, String name) {
            this.type = type;
            this.url = url;
            this.credentials = credentials;
            this.timeOutMillis = timeOutMillis;
            this.schacHomeOrganization = schacHomeOrganization;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Configuration{" +
                    "url='" + url + '\'' +
                    ", timeOutMillis=" + timeOutMillis +
                    ", schacHomeOrganization='" + schacHomeOrganization + '\'' +
                    '}';
        }

        public static class Credentials {
            final String username;
            final String password;

            public Credentials(String username, String password) {
                this.username = username;
                this.password = password;
            }
        }

    }
}
