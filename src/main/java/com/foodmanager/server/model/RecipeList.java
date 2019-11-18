package com.foodmanager.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

@JsonFormat
public class RecipeList {
    @JsonFormat
    private List<Recipe> hits;
    private double max_score;

    RecipeList(){
        hits = new ArrayList<>();
    }

    public double getMax_score() {
        return max_score;
    }

    public List<Recipe> getHits() {
        return hits;
    }

    public void setHits(List<Recipe> hits) {
        this.hits = hits;
    }

    public void setMax_score(double max_score) {
        this.max_score = max_score;
    }
}
