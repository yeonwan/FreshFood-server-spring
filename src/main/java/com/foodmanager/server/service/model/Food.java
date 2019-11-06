package com.foodmanager.server.service.model;

public class Food {
    private String name;
    private String ExpDate;
    private String memo;
    private String createdAt;

    public Food(){}
    public Food(String name, String ExpDate, String memo, String createdAt){
        this.name = name.toUpperCase();
        this.ExpDate = ExpDate;
        this.memo = memo;
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
