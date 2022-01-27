package voot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Membership implements Comparable<Membership> {

    public static final Membership MEMBER = new Membership("member");
    public static final Membership MANAGER = new Membership("manager");
    public static final Membership ADMIN = new Membership("admin");

    private final String basic;

    @JsonIgnore
    private Integer importance;

    public Membership(String basic) {
        this.basic = basic.toLowerCase();
        switch (this.basic) {
            case "prospect": {
                this.importance = 0;
                break;
            }
            case "member": {
                this.importance = 1;
                break;
            }
            case "manager": {
                this.importance = 2;
                break;
            }
            case "admin": {
                this.importance = 3;
                break;
            }
            case "owner": {
                this.importance = 4;
                break;
            }
        }

    }

    public String getBasic() {
        return basic;
    }

    @Override
    public String toString() {
        return String.format("Membership{basic='%s'}", basic);
    }

    @Override
    public int compareTo(Membership other) {
        return this.importance.compareTo(other.importance);
    }
}
