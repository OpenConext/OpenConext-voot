package voot.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Comparator;
import java.util.Objects;

public class Membership implements Comparable<Membership> {

  public static final Membership MEMBER = new Membership("member", 0);
  public static final Membership MANAGER = new Membership("manager", 1);
  public static final Membership ADMIN = new Membership("admin", 2);

  private final String basic;
  @JsonIgnore
  private final Integer importance;

  public Membership(String basic) {
    this(basic, 0);
  }

  private Membership(String basic, int importance) {
    this.basic = basic;
    this.importance = importance;
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

  @Override
  public int compareTo(Membership other) {
    return this.importance.compareTo(other.importance);
  }
}
