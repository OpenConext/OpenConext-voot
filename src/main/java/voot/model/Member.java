package voot.model;

import lombok.ToString;

@ToString
public class Member {

    public final String id;
    public final String name;
    public final String email;

    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
