package com.foodmanager.server.model;


import org.jetbrains.annotations.Contract;

public class Food {
    private int id;
    private String name;
    private String expDate;
    private String memo;
    private String uri;
    private  String category;

    @Contract(pure = true)
    public Food(){}

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getUri(){
        return uri;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void setUri(String uri) { this.uri = uri; }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
