package voot.valueobject;

public class Group {

  public final String id;
  public final String displayName;
  public final String description;
  public final Membership membership;

  public Group(String id, String displayName, String description, Membership membership) {
    this.id = id;
    this.displayName = displayName;
    this.description = description;
    this.membership = membership;

  }

  @Override
  public String toString() {
    return String.format("Group{id='%s', displayName='%s', description='%s', membership='%s'}", id, displayName, description, membership);
  }
}
