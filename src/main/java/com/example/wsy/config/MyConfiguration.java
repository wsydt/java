package com.example.wsy.config;

import com.example.wsy.dao.UserDao;
import com.example.wsy.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public UserDao userDao(){
        return new UserDao();
    }

    @Bean
    public UserService userService(){
        return new UserService(userDao());
    }

}
