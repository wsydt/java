package com.example.wsy.spring.configuration;

import com.example.wsy.spring.controller.MyController;
import com.example.wsy.spring.dao.MyDao;
import com.example.wsy.spring.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyController myController(){
        return new MyController();
    }

    @Bean
    public MyService myService(){
        return new MyService();
    }

    @Bean
    public MyDao myDao(){
        return new MyDao();
    }
}
