package com.foodmanager.server.controller;

import com.foodmanager.server.model.Recipe;
import com.foodmanager.server.services.RecipeRecommendService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendController {
    @Autowired
    private RecipeRecommendService recipeRecommendService;


    @CrossOrigin(origins = "*")
    @GetMapping("recipe/getAll")
    public ResponseEntity<Recipe> getAllRecipe(){
        return  new ResponseEntity<Recipe>(recipeRecommendService.getAll(), HttpStatus.OK);
    }

    @GetMapping("recipe/getIth/{i}")
    public ResponseEntity<String> getIth(@PathVariable long i) throws ParseException {
        return  recipeRecommendService.getIth(i);
    }

    @GetMapping("recipe/TopN/UserId={i}?size={size}")
    public ResponseEntity getTopN(@PathVariable long i, @PathVariable int size) throws ParseException {
        return recipeRecommendService.getTop10(i, size);
    }
}
