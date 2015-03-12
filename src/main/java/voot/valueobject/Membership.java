package voot.valueobject;

public class Membership {

  public static final Membership defaultMembership = new Membership("member");

  public Membership(String basic) {
    this.basic = basic;
  }

  public final String basic;

  @Override
  public String toString() {
    return String.format("Membership{basic='%s'}", basic);
  }

  public static Membership fromRole(String role) {
    return new Membership(role);
  }
}
