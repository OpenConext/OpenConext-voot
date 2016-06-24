package voot.valueobject;

import java.util.Comparator;
import java.util.Objects;

public class Membership {

  public static final Membership MEMBER = new Membership("member");
  public static final Membership MANAGER = new Membership("manager");
  public static final Membership ADMIN = new Membership("admin");

  private final String basic;

  public Membership(String basic) {
    this.basic = basic;
  }

  public String getBasic() {
    return basic;
  }

  @Override
  public String toString() {
    return String.format("Membership{basic='%s'}", basic);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Membership that = (Membership) o;
    return Objects.equals(basic, that.basic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(basic);
  }
}
