package com.foodmanager.server.service.controller;

import com.foodmanager.server.service.Repository.DBRepository;
import com.foodmanager.server.service.Repository.FoodResources;
import com.foodmanager.server.service.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FoodRegisterController {
    @Autowired
    private FoodResources foodResources;
    @Autowired
    private DBRepository dbRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/findAll")
    public ResponseEntity<List< String> > findAllFood(){
        return new ResponseEntity<>(dbRepository.getAllFood(), HttpStatus.OK);
    }

    @GetMapping("/findById&id={UserId}")
    public Food findById(@PathVariable long UserId){
        return foodResources.findById(UserId);
    }

    @PostMapping("/register")
    public ResponseEntity <Food> register(@RequestBody Food food){
        return new ResponseEntity<>(dbRepository.addFood(food),HttpStatus.OK);
    }
    @DeleteMapping("/{UserId}/deleteById")
    public Food delete(@PathVariable long UserId){
        return foodResources.deleteById(UserId);
    }
}
