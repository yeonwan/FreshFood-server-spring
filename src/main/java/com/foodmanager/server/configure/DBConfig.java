package com.foodmanager.server.configure;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("team_one");
        dataSourceBuilder.password("team_one_password");
        dataSourceBuilder.url(
                "jdbc:mysql://yonsei-aurora-primary-instance-1.cury5e9pcojj.ap-northeast-2.rds.amazonaws.com:3306/team_one");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

}
