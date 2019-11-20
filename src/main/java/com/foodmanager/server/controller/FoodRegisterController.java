package com.foodmanager.server.controller;

import com.foodmanager.server.model.Food;
import com.foodmanager.server.services.FoodHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FoodRegisterController {
    @Autowired
    private FoodHandleService foodHandleService;

    @CrossOrigin(origins = "*")
    @GetMapping("{UserId}/findAll/foods")
    public ResponseEntity<?> findAllFood(@PathVariable long UserId){
        return foodHandleService.getAllFoodByUserId(UserId);
    }

    @PostMapping("{UserId}/register/foods")
    public ResponseEntity<?> register(@PathVariable long UserId, @RequestBody Food food){
        return foodHandleService.addFoodToRefri(UserId, food);
    }

    @DeleteMapping("{UserId}/deleteById={FoodId}/foods")
    public ResponseEntity delete(@PathVariable long UserId, @PathVariable long FoodId){
        return foodHandleService.DeleteById(UserId,FoodId);
    }

    @DeleteMapping("food/{UserId}/deleteAll")
    public ResponseEntity deleteAll(@PathVariable long UserId){
        return foodHandleService.DeleteAll(UserId);
    }

    @PostMapping("food/food/register")
    public ResponseEntity register( @RequestBody Food food){
        return foodHandleService.addFood(food);
    }
}
