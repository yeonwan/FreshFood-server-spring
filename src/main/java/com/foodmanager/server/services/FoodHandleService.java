package com.foodmanager.server.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.foodmanager.server.configure.S3Config;
import com.foodmanager.server.model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodmanager.server.repository.DBRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class FoodHandleService {
    private Logger logger = LoggerFactory.getLogger(FoodHandleService.class);
    @Autowired
    private DBRepository dbRepository;
    @Autowired
    private S3Config s3Config;
    @Autowired
    private AmazonS3 amazonS3;

    public ResponseEntity addFoodToRefri(long UserId, Food food){
        String sql = String.format("INSERT INTO Refri" +
                        "(User_ID, Food_ID, Exdate, Memo, Category, Uri)" +
                        " VALUES(%d, %d, \"%s\", \"%s\", \"%s\", \"%s\")",
                UserId, food.getId(), food.getExpDate(), food.getMemo(), food.getCategory(), food.getUrl());
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity DeleteById(long UserId, long FoodId){
        String sql = "";
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity DeleteAll(long UserId){
        String  sql = "";
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Integer> findFoodId(String fName){
        String sql = String.format("SELECT Name FROM Food WHERE ID = \"%s\"", fName);
        return new ResponseEntity<Integer>(dbRepository.queryForObject(sql, Integer.class), HttpStatus.OK);
    }

    public ResponseEntity <List<Food>> getAllFoodByUserId(long UserId){
        String sql = "SELECT FooD_ID as food_id, " +
                "(SELECT Name FROM Food WHERE ID = food_id) AS name, " +
                "Memo as memo, " +
                "Category as category, " +
                "Uri as uri, " +
                "Exdate as expDate " +
                "FROM Refri WHERE User_ID =" + UserId;
        return new ResponseEntity<>(dbRepository.queryForList(sql, Food.class),HttpStatus.OK);
    }

    public ResponseEntity addFood(Food food){
        String sql = String.format("INSERT INTO Food (Name, Term) VALUES(%s, %s)", food.getName(), 0);
        dbRepository.execute(sql);
        return new ResponseEntity(HttpStatus.OK);
    }
}
