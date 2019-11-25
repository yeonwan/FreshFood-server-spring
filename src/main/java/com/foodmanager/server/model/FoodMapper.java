package com.foodmanager.server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class FoodMapper implements RowMapper<Food> {
    public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
        Food food = new Food();
        food.setId(rs.getInt("food_id"));
        food.setName(rs.getString("name"));
        food.setUrl(rs.getString("uri"));
        food.setCategory(rs.getString("category"));
        food.setExpDate(rs.getString("expDate"));
        food.setMemo(rs.getString("memo"));
        return food;
    }
}
