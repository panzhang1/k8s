package com.sf.hop.user.model;

public class User {
    private String id;
    private String name;
    private String firstName;
    private String lastName;

    public User() {
    }
    
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }
}