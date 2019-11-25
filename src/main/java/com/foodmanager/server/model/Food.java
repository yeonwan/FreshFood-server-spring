package com.foodmanager.server.model;


public class Food {
    private int id;
    private String name;
    private String expDate;
    private String memo;
    private String uri;
    private  String category;

    public Food(){}

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl(){return uri;}

    public String getExpDate() {
        return expDate;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void setUrl(String url) { this.uri = uri; }

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
