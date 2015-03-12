package voot.provider;

import voot.valueobject.Group;

import java.util.List;
import java.util.Optional;

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
   * @param schacHomeOrganization the end-user's schacHomeOrg
   * @param groupId the group being requested (can be fully qualified persistent name or the unqualified Institution name)
   * @return true if this Provider can return groups for the specified schacHomeOrganization
   */
  boolean shouldBeQueriedForGroup(String schacHomeOrganization, String groupId);

  List<Group> getGroupMemberships(String uid);

  Optional<Group> getGroupMembership(String uid, String groupId);

  static class Configuration {

    public final GroupProviderType type;
    public final String url;
    public final Credentials credentials;
    public final Integer timeOutMillis;
    public final String schacHomeOrganization;

    public Configuration(GroupProviderType type, String url, Credentials credentials, Integer timeOutMillis, String schacHomeOrganization) {
      this.type = type;
      this.url = url;
      this.credentials = credentials;
      this.timeOutMillis = timeOutMillis;
      this.schacHomeOrganization = schacHomeOrganization;
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
