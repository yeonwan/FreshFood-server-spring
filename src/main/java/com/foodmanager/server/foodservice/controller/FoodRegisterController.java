package com.foodmanager.server.foodservice.controller;

import com.foodmanager.server.repository.DBRepository; // Data access object
import com.foodmanager.server.foodservice.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FoodRegisterController {

    @Autowired
    private DBRepository dbRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/{UserId}/findAll")
    public ResponseEntity<List< Food> > findAllFood(@PathVariable long UserId){
        return new ResponseEntity< List<Food> > (dbRepository.getAllFood(UserId), HttpStatus.OK);
    }

    @PostMapping("/{UserId}/register")
    public ResponseEntity <Food> register(@PathVariable long UserId, @RequestBody Food food){
            System.out.println(food.getExpDate());
        return new ResponseEntity<>(dbRepository.addFoodToRefri(UserId, food), HttpStatus.OK);
    }

    @DeleteMapping("/{UserId}/deleteById={FoodId}")
    public Food delete(@PathVariable long UserId, @PathVariable long FoodId){
        return new Food();
    }

    @PostMapping("/food/register")
    public ResponseEntity <Food> register( @RequestBody Food food){
        return new ResponseEntity<>(dbRepository.addFood(food), HttpStatus.OK);
    }


}
