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

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


@Service
@Slf4j
public class RecipeRecommendService {
    private String Uri = System.getenv("elasticUrl")+"recipes/recipe/_search";
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
        return CompletableFuture.supplyAsync(()-> {
            try {
                return searchQuery(words);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            } }, fixedThreadPool)
                .thenApplyAsync((query) -> {
                    try {
                        return RestTemplateUtils.postResponseEntity(Uri , query);
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
        return CompletableFuture.supplyAsync(() -> (getUserFood(UserId)), cachedThreadPool)
                .thenApplyAsync((sources)-> {
                    try {

                        return recommendQuery("\""+sources.toString()+"\"", size);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }, fixedThreadPool)
                .thenApplyAsync((query) -> {
                    try {
                        return RestTemplateUtils.postResponseEntity(Uri , query);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                }, cachedThreadPool)
                .thenApplyAsync((responseEntity)->{
                    try {
                        Set<String> hashSet = new HashSet<>(100);
                        hashSet.addAll(getUserFood(UserId));
                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObject =
                                (JSONObject) jsonParser.parse(Objects.requireNonNull(responseEntity.getBody()));
                        JSONObject hits = (JSONObject) jsonObject.get("hits");
                        JSONArray hitss = (JSONArray)hits.get("hits");
                        JSONArray jsonArray = new JSONArray();
                        for (Object o : hitss) {
                            JSONObject tmp = (JSONObject) o;
                            JSONObject recipe = (JSONObject)tmp.get("_source");
                            JSONArray sources = (JSONArray)recipe.get("재료");
                            int ctr = 0;
                            double len = (double)sources.size();
                            for(Object obj: sources){
                                JSONObject s = (JSONObject)obj;
                                String s1 = (s.get("식자재명").toString());
                                if(hashSet.contains(s1)) {
                                    s.put("보유", 1); ctr++;
                                }
                                else s.put("보유", 0);
                            }
                            recipe.put("매치율", ctr/len);
                            jsonArray.add(recipe);
                        }
                        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
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
    private List<String> getUserFood(long UserId) {
        List<String> result = Objects.requireNonNull(foodHandleService.getAllFoodNameByUserId(UserId).getBody());
        //String result = "\"양파, 삼겹살, 햄, 김, 김치\"";
        logger.info(result.toString());
        return  new ArrayList<>(result);
    }
}
