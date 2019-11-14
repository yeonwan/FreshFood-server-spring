package com.foodmanager.server.recipeservice.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Recipe {
    private String name;
    private ArrayList<String> sources;
    private ArrayList<String> steps;
    private String s3url;
    private HashMap<String, String> infos;

    public Recipe(){
        sources=  new ArrayList<String>();
        steps = new  ArrayList<String>();
        infos = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public String getS3url() {
        return s3url;
    }

    public HashMap<String, String> getInfos() {
        return infos;
    }

    public void setS3url(String s3url) {
        this.s3url = s3url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSources(ArrayList<String> sources) {
        this.sources = sources;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public void setInfos(HashMap<String, String> infos) {
        this.infos = infos;
    }
}
