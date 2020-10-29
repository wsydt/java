package com.example.wsy.spring.factory;

import com.example.wsy.spring.bean.Cloth;
import com.example.wsy.spring.bean.User;

import java.util.List;

public class MyBeanFactory {

    public User getUser(){
        return new User();
    }

    public User getUser(String name, int age) {
        return new User(name, age);
    }

    public static User getUser(String name) {
        return new User(name, 18);
    }

    public User getUser(List<Object> phones) {
        return new User(phones);
    }

    public User getUser(String name, int age, List<Object> phones){
        return new User(name, age, phones);
    }

    public User getUser(List<Object> phones, List<Cloth> tags) {
        return new User(phones, tags);
    }
}
