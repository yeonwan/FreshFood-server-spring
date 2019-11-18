package com.foodmanager.server.services;

import com.foodmanager.server.model.Recipe;
import com.foodmanager.server.utils.RestTemplateUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class RecipeRecommendService {
    private String Uri = "http://ec2-15-164-218-30.ap-northeast-2.compute.amazonaws.com:9200/";
    private Logger logger = LoggerFactory.getLogger(RecipeRecommendService.class);
    @Autowired
    private FoodHandleService foodHandleService;

    public Recipe getAll() {
        String url = "test/recipe/_search";
        return (Recipe) RestTemplateUtils.getJsonForObject(Uri + url, Recipe.class);
    }

    public ResponseEntity<String> getIth(long i) throws ParseException {
        String url = "test/recipe/"+i;
        return RestTemplateUtils.getResponseEntity(Uri + url);
    }

    public ResponseEntity<JSONArray> getTop10(long UserId, int size) throws ParseException{
        String url = "test/recipe/_search";
        ArrayList<String> arr = new ArrayList<>();
        arr.add("삼겹살");
        arr.add("소고기");
        arr.add("당근");
        String sources = "\""+arr.toString() +"\"";
                //Objects.requireNonNull(foodHandleService.getAllFood(UserId).getBody()).toString();
        ResponseEntity<String> responseEntity =
                RestTemplateUtils.postResponseEntity(Uri+url, makeQuery(sources, 10));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)
                jsonParser.parse(Objects.requireNonNull(responseEntity.getBody()));
        JSONObject hits = (JSONObject) jsonObject.get("hits");
        JSONArray recipes = (JSONArray) hits.get("hits");
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    private String makeQuery(String sources, int size) throws ParseException {
        return String.format("{\"query\": {\"function_score\": {\"query\": {\"multi_match\": {\"query\":%s,\"fields\": " +
                "[\"재료.식자재명\",\"제목^0.5\"] }}, \"random_score\": {\"field\": \"_seq_no\"} } }, \"size\": \"%d\"" +
                "}", sources,size);
    }

}