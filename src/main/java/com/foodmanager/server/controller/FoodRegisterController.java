package com.foodmanager.server.controller;

import com.foodmanager.server.model.Food;
import com.foodmanager.server.services.FoodHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodRegisterController {

    @Autowired
    private FoodHandleService foodHandleService;

    @CrossOrigin(origins = "*")
    @GetMapping("foods/findAll/{UserId}")
    public ResponseEntity<?> findAllFood(@PathVariable long UserId){
        return foodHandleService.getAllFoodByUserId(UserId);
    }

    @PostMapping("foods/register/{UserId}")
    public ResponseEntity<?> register(@PathVariable long UserId, @RequestBody Food food){
        return foodHandleService.addFoodToRefri(UserId, food);
    }

    @DeleteMapping("foods/{UserId}/deleteById={FoodId}")
    public ResponseEntity delete(@PathVariable long UserId, @PathVariable long FoodId){
        return foodHandleService.DeleteById(UserId,FoodId);
    }

    @DeleteMapping("foods/deleteAll/{UserId}")
    public ResponseEntity deleteAll(@PathVariable long UserId){
        return foodHandleService.DeleteAll(UserId);
    }

    @PutMapping("foods/registerFood")
    public ResponseEntity register( @RequestBody Food food){
        return foodHandleService.addFood(food);
    }
}
