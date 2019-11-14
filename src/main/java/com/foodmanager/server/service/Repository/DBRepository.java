package com.foodmanager.server.service.Repository;

import com.foodmanager.server.service.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<String> getAllFood(){
        return new ArrayList<>(jdbcTemplate.queryForList("SELECT NAME FROM FOOD_NAME", String.class));
    }

    public Food addFoodToRefri(int UserId, Food food){
        jdbcTemplate.execute(String.format("INSERT INTO Refri" +
                        "(User_ID, Food_ID, Exdate, Memo, Category, Uri)" +
                        " VALUES(%d, %d, %s, %s, %s, %s)",
                UserId, food.getId(), food.getExpDate(),food.getMemo(),food.getCategory(), food.getUrl()));
        return food;
    }

    public int findFoodId(String fname){
        int re = -1;
        try{
         re = jdbcTemplate.queryForObject(String.format("SELECT ID FROM FOOD WHERE Name = %s",fname), Integer.class);
        }
        catch (NullPointerException e){
            return -1;
        }
        return re;
    }

    public Food addFood(Food food){
        jdbcTemplate.execute(String.format("INSERT INTO Food" +
                        "(Name, Term)" +
                        " VALUES(%s, %s)",
                food.getName(), 0));
        return food;
    }
}
