package com.pluralsight.conferenceScheduledemo.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//any methods defined here can return bean definitions that can be stored in the 
//spring context
@Configuration
public class PersistenceConfiguration {
    
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        //url == url from application.properties
        builder.url("jdbc:mysql://localhost:8080/conference_app");
        System.out.println("Custom datasource bean initialized and set");
        return builder.build();
    }
}
