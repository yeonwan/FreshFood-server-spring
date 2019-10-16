package com.education.sample.service.sendMessage;

public class RequestClass {

    public String userName;

    public RequestClass() {}

    public RequestClass(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
