package com.foodmanager.server.controller;

import com.foodmanager.server.model.Food;
import com.foodmanager.server.services.FoodHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class FoodRegisterController {

    @Autowired
    private FoodHandleService foodRegisterService;

    @CrossOrigin(origins = "*")
    @GetMapping("food/{UserId}/findAll")
    public Flux<Food> findAllFood(@PathVariable long UserId){
        return foodRegisterService.getAllFood(UserId);
    }

    @PostMapping("food/{UserId}/register")
    public ResponseEntity register(@PathVariable long UserId, @RequestBody Food food){
        return foodRegisterService.addFoodToRefri(UserId, food);
    }

    @DeleteMapping("food/{UserId}/deleteById={FoodId}")
    public ResponseEntity delete(@PathVariable long UserId, @PathVariable long FoodId){
        return foodRegisterService.DeleteById(UserId,FoodId);
    }

    @DeleteMapping("food/{UserId}/deleteAll")
    public ResponseEntity deleteAll(@PathVariable long UserId){
        return foodRegisterService.DeleteAll(UserId);
    }

    @PostMapping("food/food/register")
    public ResponseEntity register( @RequestBody Food food){
        return foodRegisterService.addFood(food);
    }
}
