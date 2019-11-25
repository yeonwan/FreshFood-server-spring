package com.foodmanager.server.controller;

import com.foodmanager.server.services.RecipeRecommendService;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
public class RecommendController {
    @Autowired
    private RecipeRecommendService recipeRecommendService;

    @CrossOrigin(origins = "*")
    @GetMapping("recipes/search/{words}")
    public CompletableFuture<ResponseEntity<JSONArray>> getAllRecipe(@PathVariable String words) throws ParseException {
        return recipeRecommendService.searchByName(words);
    }

    @GetMapping("recipes/topN/UserId={i}/size={size}")
    public CompletableFuture<ResponseEntity<JSONArray>> getTopN(@PathVariable long i, @PathVariable int size) throws ParseException {
        return recipeRecommendService.getTopN(i, size);
    }
}
