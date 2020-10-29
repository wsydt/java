package com.example.wsy.spring.aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDao {

    public void save(){
        System.out.println("save DB success :: Test");
    }

    public List<String> getUser(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(String.valueOf(i));
        }
        System.out.println("TestDao::getUser");
        return result;
    }
}
