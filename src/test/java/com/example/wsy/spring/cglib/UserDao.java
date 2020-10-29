package com.example.wsy.spring.cglib;

public class UserDao implements IUserDao {

    @Override
    public void save(){
        System.out.println("Save DB: userDao");
    }

}
