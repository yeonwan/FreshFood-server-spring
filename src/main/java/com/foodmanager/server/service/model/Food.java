package com.foodmanager.server.service.model;

public class Food {
    private int id;
    private String name;
    private String ExpDate;
    private String memo;
    private String url;
    private  String category;
    private  String ExDate;

    public Food(){}
    public Food(String name, String ExpDate, String memo, String url, String category, int id){
        this.id = id;
        this.name = name.toLowerCase();
        this.ExpDate = ExpDate;
        this.memo = memo;
        this.url = url;
        this.category= category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getExDate() {
        return ExDate;
    }

    public String getUrl(){return url;}

    public String getExpDate() {
        return ExpDate;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void setUrl(String url) { this.url = url; }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
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
}
