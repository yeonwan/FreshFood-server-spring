package com.foodmanager.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public <T> T queryForObject(String sql, Class<T> clazz){
        return jdbcTemplate.queryForObject(sql, clazz);
    }

    public <T> List<T> queryForList(String sql, Class<T> clazz) {
        return new ArrayList<T>(jdbcTemplate.queryForList(sql, clazz));
    }

    public <T> List<T> queryForClassList(String sql, RowMapper<T> rowMapper){
        return new ArrayList<T>(jdbcTemplate.query(sql,rowMapper));
    }

    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }
}

