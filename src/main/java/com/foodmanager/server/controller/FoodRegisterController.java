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
    @GetMapping("foods/findAll/{UserId}") //유저 아이디로 냉장고 안에 그 유저의 음식들 다 가져오기.
    public ResponseEntity<?> findAllFood(@PathVariable long UserId){
        return foodHandleService.getAllFoodByUserId(UserId);
    }

    @PostMapping("foods/register/{UserId}") // 유저아이디로 냉장고 안에 음식 등록
    public ResponseEntity<?> register(@PathVariable long UserId, @RequestBody Food food){
        return foodHandleService.addFoodToRefri(UserId, food);
    }

    @DeleteMapping("foods/deleteAll/{UserId}")
    public ResponseEntity deleteAll(@PathVariable long UserId){
        return foodHandleService.DeleteAll(UserId);
    }

    @DeleteMapping("foods/deleteById/{UserId}/{FoodName}")
    public ResponseEntity deleteById(@PathVariable long UserId, @PathVariable String FoodName){
        return foodHandleService.DeleteById(UserId,FoodName);
    }

    @PutMapping("foods/registerFood")
    public ResponseEntity register( @RequestBody Food food){
        return foodHandleService.addFood(food);
    }
}
