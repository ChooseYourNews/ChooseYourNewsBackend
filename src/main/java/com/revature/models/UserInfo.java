package com.revature.models;

public class UserInfo {
    public String firstName = null;
    public String lastName = null;

    public UserInfo () {
        super();
    }

    public UserInfo(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
