package com.foodmanager.server.foodservice.model;

public class User {
    private long userId;
    private String userName;

    public User() {}

    public User(long id, String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
