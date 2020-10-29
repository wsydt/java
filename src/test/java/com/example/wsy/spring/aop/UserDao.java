package com.example.wsy.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class UserDao implements IUserDao {

    @Override
    public void save(){
        System.out.println("package aop save DB()");
    }

}
