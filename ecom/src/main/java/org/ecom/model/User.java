package org.ecom.model;

import org.ecom.enums.UserMemberShip;

public class User {
    private int id;
    private String name;
    private UserMemberShip membership;

    public User() {
    }

    public User(int id, UserMemberShip membership, String name) {
        this.id = id;
        this.membership = membership;
        this.name = name;
    }

    public User(UserMemberShip membership, String name) {
        this.membership = membership;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserMemberShip getMembership() {
        return membership;
    }

    public void setMembership(UserMemberShip membership) {
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", membership=" + membership +
                '}';
    }
}
