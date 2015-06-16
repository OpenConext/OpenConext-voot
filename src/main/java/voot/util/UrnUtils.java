package voot.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convenience functions for dealing with group and person-urn's.
 */
public class UrnUtils {

  // the application deals with converting between global and local URN-values. Register the patterns here.
  public static final String URN_COLLAB_GROUP_REGEXP = "^urn:collab:group:([^:]+):(.+)$";
  public static final Pattern GROUP_PATTERN = Pattern.compile(URN_COLLAB_GROUP_REGEXP);
  public static final String URN_COLLAB_PERSON_REGEXP = "^urn:collab:person:([^:]+):(.+)$";
  public static final Pattern PERSON_PATTERN = Pattern.compile(URN_COLLAB_PERSON_REGEXP);

  /**
   * Strip groupId, e.g. removing urn:collab:group:schacHomeOrganization:stripped-groupId and returning remaining stripped-groupId part
   * @param groupId the id that should addhere to {@value #URN_COLLAB_GROUP_REGEXP}
   * @return the stripped value or the empty Optional when stripping did not succeeed

   */
  public static Optional<String> extractLocalGroupId(final String groupId) {
    return getIdFromRegExp(GROUP_PATTERN, groupId);
  }

  /**
   * Strip uid, e.g. removing urn:collab:person:schacHomeOrganization:stripped-uid and returning remaining stripped-ui part
   * @param uid the uid that should addhere to {@value #URN_COLLAB_PERSON_REGEXP}
   * @return the stripped value or the empty Optional when stripping did not succeeed
   */
  public static Optional<String> extractLocalUid(final String uid) {
    return getIdFromRegExp(PERSON_PATTERN, uid);
  }

  public static Optional<String> getSchacHomeFromGroupUrn(String groupId) {
    return getSchacHomeFromRegExp(GROUP_PATTERN, groupId);
  }

  public static Optional<String> getSchacHomeFromPersonUrn(String personId) {
    return getSchacHomeFromRegExp(PERSON_PATTERN, personId);
  }

  public static boolean isFullyQualifiedGroupName(String groupId) {
    return GROUP_PATTERN.matcher(groupId).matches();
  }

  private static Optional<String> getIdFromRegExp(Pattern pattern, final String id) {
    Matcher m = pattern.matcher(id);
    if (m.matches()) {
      return Optional.of(m.group(2));
    }
    return Optional.empty();
  }

  private static Optional<String> getSchacHomeFromRegExp(Pattern pattern, String id) {
    Matcher m = pattern.matcher(id);
    if (m.matches()) {
      return Optional.of(m.group(1));
    } else {
      return Optional.empty();
    }
  }
}
