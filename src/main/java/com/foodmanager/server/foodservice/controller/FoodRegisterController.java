package com.foodmanager.server.foodservice.controller;

import com.foodmanager.server.Repository.DBRepository; // Data access object
import com.foodmanager.server.foodservice.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@RestController
public class FoodRegisterController {
    private static Logger logger = LoggerFactory.getLogger(FoodRegisterController.class);
    @Autowired
    private DBRepository dbRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/findAll")
    public ResponseEntity<List< String> > findAllFood(){
        return new ResponseEntity<>(dbRepository.getAllFood(), HttpStatus.OK);
    }

    @GetMapping("/findById&id={UserId}")
    public Food findById(@PathVariable long UserId){
        //return foodResources.findById(UserId);
        return new Food();
        //TODO
    }

    @PostMapping("/food/register")
    public ResponseEntity <Food> register( @RequestBody Food food){
        return new ResponseEntity<>(dbRepository.addFood(food), HttpStatus.OK);
    }

    @PostMapping("/{UserId}/register")
    public ResponseEntity <Food> register(@PathVariable int UserId, @RequestBody Food food){
            System.out.println(food.getExpDate());
        return new ResponseEntity<>(dbRepository.addFoodToRefri(UserId, food), HttpStatus.OK);
    }

    @DeleteMapping("/{UserId}/deleteById")
    public Food delete(@PathVariable long UserId){
        //return foodResources.deleteById(UserId);
        return new Food();
    }
}
