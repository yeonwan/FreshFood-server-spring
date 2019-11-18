package com.foodmanager.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.json.JSONObject;

@JsonFormat
public class Recipe {
    @JsonFormat
    private JSONObject _source;
    private double score;
    private long _id;

    public Recipe(){
        _source = new JSONObject();
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void set_source(JSONObject _source) {
        this._source = _source;
    }

   public JSONObject get_source() {
        return _source;
   }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
   }
}
