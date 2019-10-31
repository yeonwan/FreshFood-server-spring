package com.foodmanager.server.service.foodregister.Resources;

import com.foodmanager.server.service.foodregister.model.Food;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FoodResources {
    private HashMap<Long, Food> Foods = new HashMap<>();
    public FoodResources(){
        //Foods.add(new Food("default", "hi", "hello" , "fuck"));
    }

    public Map<Long, Food> findAll(){
        return Foods;
    }

    public Food findById(long id){
        return Foods.get(id);
    }

    public Food register(long UserId, Food food){
        Foods.put(UserId, food);
        return food;
    }

    public Food deleteById(long id){
        Food food = Foods.get(id);
        if( food != null) Foods.remove(id);
        return food;
    }
}
