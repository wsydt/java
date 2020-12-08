package com.example.wsy.spring.configuration;

import com.example.wsy.spring.controller.TestImportController;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MyConfig {


    @Bean
    public TestImportController importController() {
        return new TestImportController();
    }



}
