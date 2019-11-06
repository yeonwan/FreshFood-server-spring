package com.foodmanager.server.service.configure;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("fm");
        dataSourceBuilder.password("foodmanager!!");
        dataSourceBuilder.url(
                "jdbc:mysql://fm-db-test.cluster-cury5e9pcojj.ap-northeast-2.rds.amazonaws.com:3306/FFcompany");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

}
