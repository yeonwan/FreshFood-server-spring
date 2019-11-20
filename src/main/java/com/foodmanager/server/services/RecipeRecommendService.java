package com.foodmanager.server.services;

import com.foodmanager.server.utils.RestTemplateUtils;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


@Service
@Slf4j
public class RecipeRecommendService {
    private String Uri = "http://ec2-15-164-218-30.ap-northeast-2.compute.amazonaws.com:9200/";
    private Logger logger = LoggerFactory.getLogger(RecipeRecommendService.class);
    @Autowired
    private FoodHandleService foodHandleService;

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService fixedThreadPool;

    @Autowired
    @Qualifier("cachedThreadPool")
    private ExecutorService cachedThreadPool;

    public CompletableFuture<ResponseEntity<JSONArray>> searchByName(String words) throws ParseException {
        String url = "test/recipe/_search";
        return CompletableFuture.supplyAsync(()-> {
            try {
                return searchQuery(words);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            } }, fixedThreadPool)
                .thenApplyAsync((query) -> {
                    try {
                        return RestTemplateUtils.postResponseEntity(Uri+url , query);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }, cachedThreadPool)
                .thenApplyAsync((responseEntity)->{
                    try {
                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObject =
                                (JSONObject) jsonParser.parse(Objects.requireNonNull(responseEntity.getBody()));
                        JSONObject hits = (JSONObject) jsonObject.get("hits");
                        return new ResponseEntity<>((JSONArray)hits.get("hits"),HttpStatus.OK);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new ResponseEntity<JSONArray>(HttpStatus.BAD_REQUEST);
                },fixedThreadPool);
    }

    public CompletableFuture<ResponseEntity<JSONArray>> getTopN(long UserId, int size) throws ParseException{
        String url = "test/recipe/_search";
        //"\"양파, 삼겹살, 햄, 김, 김치\""
        return CompletableFuture.supplyAsync(() -> (getUserFood(UserId)), cachedThreadPool)
                .thenApplyAsync((sources)-> {
                    try {
                        return recommendQuery(sources, size);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }, fixedThreadPool)
                .thenApplyAsync((query) -> {
                    try {
                        return RestTemplateUtils.postResponseEntity(Uri+url , query);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }, cachedThreadPool)
                .thenApplyAsync((responseEntity)->{
                    try {
                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObject =
                                (JSONObject) jsonParser.parse(Objects.requireNonNull(responseEntity.getBody()));
                        JSONObject hits = (JSONObject) jsonObject.get("hits");
                        return new ResponseEntity<>((JSONArray)hits.get("hits"),HttpStatus.OK);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new ResponseEntity<JSONArray>(HttpStatus.BAD_REQUEST);
                }, fixedThreadPool);
    }

    private String recommendQuery(String sources, int size) throws ParseException {
        if(sources.equals("\"[]\"")) return String.format("{\"query\": {\"function_score\": { \"random_score\": {\"field\": \"_seq_no\"} } }, \"size\": %d" +
                    "}",size);
        else return String.format("{\"query\": {\"function_score\": {\"query\": {\"multi_match\": {\"query\":%s,\"fields\": " +
                "[\"재료.식자재명\",\"제목^0.5\"] }}, \"random_score\": {\"field\": \"_seq_no\"} } }, \"size\": %d" +
                "}", sources,size);
    }

    private String searchQuery(String words) throws ParseException {
        return String.format("{\"query\": {\"function_score\": {\"query\": {\"multi_match\": {\"query\":%s,\"fields\": " +
                "[\"재료.식자재명\",\"제목^0.5\"] }}, \"random_score\": {\"field\": \"_seq_no\"} } }, \"size\": 20" +
                "}", words);
    }

    @NotNull
    private String getUserFood(long UserId) {
        return "\"" + Objects.requireNonNull(foodHandleService.getAllFoodByUserId(UserId).getBody()).toString() + "\"";
    }
}
