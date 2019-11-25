package com.foodmanager.server.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DBConfig {
    private Logger logger = LoggerFactory.getLogger(DBConfig.class);
    @Bean
    public DataSource getDataSource(){
        logger.info("Building DB connection");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(System.getenv("DB_ID"));
        dataSourceBuilder.password(System.getenv("DB_PASSWORD"));
        dataSourceBuilder.url(
                "jdbc:mysql://yonsei-aurora-primary-instance-1.cury5e9pcojj.ap-northeast-2.rds.amazonaws.com:3306/team_one?useUnicode=true&characterEncoding=utf8");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }
}
