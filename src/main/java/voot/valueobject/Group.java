package voot.valueobject;

import java.util.Objects;

public class Group {

  public final String id;
  public final String displayName;
  public final String description;
  public final String sourceID;
  public final Membership membership;

  public Group(String id, String displayName, String description, String sourceID, Membership membership) {
    this.id = id;
    this.displayName = displayName;
    this.description = description;
    this.sourceID = sourceID;
    this.membership = membership;
  }

  @Override
  public String toString() {
    return String.format("Group{id='%s', displayName='%s', description='%s', membership='%s'}", id, displayName, description, membership);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Group group = (Group) o;
    return Objects.equals(id, group.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
