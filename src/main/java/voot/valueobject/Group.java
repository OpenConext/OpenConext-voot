package voot.valueobject;

public class Group {

  public final String displayName;
  public final String id;

  public Group(String displayName, String id) {
    this.displayName = displayName;
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format("Group{id='%s', displayName='%s'}", id, displayName);
  }
}
