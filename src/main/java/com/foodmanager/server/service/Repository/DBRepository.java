package com.foodmanager.server.service.Repository;

import com.foodmanager.server.service.configure.DBConfig;
import com.foodmanager.server.service.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private long id = 100;

    public List<String> getAllFood(){
        return new ArrayList<>(jdbcTemplate.queryForList("SELECT NAME FROM FOOD_NAME", String.class));
    }

    public Food addFood(Food food){
        jdbcTemplate.execute("INSERT INTO FOOD_NAME(ID, NAME) VALUE(" + id + ",\"" + food.getName()+"\")");
        id++;
        return food;
    }

}
