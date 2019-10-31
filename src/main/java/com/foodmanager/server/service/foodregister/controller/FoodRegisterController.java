package com.foodmanager.server.service.foodregister.controller;

import com.foodmanager.server.service.foodregister.Resources.FoodResources;
import com.foodmanager.server.service.foodregister.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class FoodRegisterController {
    @Autowired
    private FoodResources foodResources;

    @CrossOrigin(origins = "*")
    @GetMapping("{UserId}/findAll")
    public Map<Long, Food> findAllFood(){
        return foodResources.findAll();
    }

    @GetMapping("{UserId}/findById")
    public Food findById(@PathVariable int UserId){
        return foodResources.findById(UserId);
    }

    @PostMapping("/{UserId}/register")
    public Food register(@PathVariable long UserId, @RequestBody Food food){
        return foodResources.register(UserId, food);
    }

    @DeleteMapping("/{UserId}/deleteById")
    public Food delete(@PathVariable long UserId){
        return foodResources.deleteById(UserId);
    }
}
