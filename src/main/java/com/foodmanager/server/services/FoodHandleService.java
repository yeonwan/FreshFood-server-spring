package com.foodmanager.server.services;

import com.amazonaws.services.xray.model.Http;
import com.foodmanager.server.model.Food;
import com.foodmanager.server.model.FoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodmanager.server.repository.DBRepository;

import java.util.List;


@Service
public class FoodHandleService {
    private Logger logger = LoggerFactory.getLogger(FoodHandleService.class);
    @Autowired
    private DBRepository dbRepository;

    public ResponseEntity addFoodToRefri(long UserId, Food food){
        food.setUri(
                String.format("https://fm-foodpicturebucket.s3.ap-northeast-2.amazonaws.com/foods/%d/%s", UserId, food.getName() +".jpg")
    );
        try {
            food.setId(dbRepository.queryForObject(String.format("SELECT ID FROM Food WHERE Name=\"%s\"", food.getName()), Integer.class));
        }catch (Exception ignored){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        logger.info(String.valueOf(UserId), food);
        String sql = String.format("INSERT INTO Refri" +
                        "(User_ID, Food_ID, Exdate, Memo, Category, Uri)" +
                        " VALUES(%d, %d, \"%s\", \"%s\", \"%s\", \"%s\")",
                UserId, food.getId(), food.getExpDate(), food.getMemo(), food.getCategory(), food.getUri());
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity DeleteById(long UserId, String FoodName){
        String sql = String.format("DELETE FROM Refri WHERE User_ID = %d AND Food_ID = %d",
                UserId, findFoodId(FoodName));
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity DeleteAll(long UserId){
        String  sql = "DELETE FROM Refri WHERE User_ID = "+UserId;
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    private int findFoodId(String fName){
        String sql = String.format("SELECT ID FROM Food WHERE Name = \"%s\"", fName);
        return dbRepository.queryForObject(sql, Integer.class);
    }

    public ResponseEntity <List<Food>> getAllFoodByUserId(long UserId){
        String sql = "SELECT FooD_ID as food_id, " +
                "(SELECT Name FROM Food WHERE ID = food_id) AS name, Memo as memo, Category as category, " +
                "Uri as uri, " +
                "Exdate as expDate " +
                "FROM Refri WHERE User_ID =" + UserId + " ORDER BY expDate ASC" ;
        return new ResponseEntity<>(dbRepository.queryForClassList(sql, new FoodMapper()),HttpStatus.OK);
    }

    public ResponseEntity <List<String>> getAllFoodNameByUserId(long UserId){
        String sql =String.format(
                "SELECT Name FROM Food WHERE ID = ANY (SELECT FooD_ID FROM Refri WHERE User_ID = %d)",UserId);
        return new ResponseEntity<>(dbRepository.queryForList(sql, String.class),HttpStatus.OK);
    }

    public ResponseEntity addFood(Food food){
        String sql = String.format("INSERT INTO Food (Name, Term)" +
                        "SELECT \"%s\", %d FROM Food WHERE NOT EXISTS" +
                        "(SELECT Name FROM Food WHERE Name = \"%s\") LIMIT 1",
                food.getName(), 0,food.getName());
        logger.info(food.getName());
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }
}
