package com.foodmanager.server.repository;

import com.foodmanager.server.foodservice.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Food> getAllFood(long UserId){
        return new ArrayList<Food>(jdbcTemplate.queryForList(
                "SELECT Food.Name AS name" +
                        " , Food.Term + Refri.Exdate AS expDate" +
                        " , Memo AS memo" +
                        " ,Category AS category," +
                        " Refri.Uri AS uri" +
                        "  FROM Refri ,Food  , User" +
                        " WHERE Food.ID = Refri.Food_ID AND Refri.User_ID = User.ID AND User.UserID = "+ UserId,
                Food.class)
        );
    }

    public Food addFoodToRefri(long UserId, Food food){
        jdbcTemplate.execute(String.format("INSERT INTO Refri" +
                        "(User_ID, Food_ID, Exdate, Memo, Category, Uri)" +
                        " VALUES(%d, %d, \"%s\", \"%s\", \"%s\", \"%s\")",
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
