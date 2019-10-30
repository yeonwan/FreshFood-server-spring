package com.foodmanager.server.service.foodregister.controller;

import com.foodmanager.server.service.foodregister.model.Food;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class FoodRegisterController {
    @CrossOrigin(origins = "*")
    @RequestMapping("/{UserId}/register")
    public List<Food> RegisterFood(){
        return Arrays.asList(
                new Food("onion","2019-12-25","Fucking","2019-10-30"),
                new Food("beef","2019-12-25","hi","2019-10-30")
                );
    }
}
