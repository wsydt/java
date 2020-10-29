package com.example.wsy.spring.service;

import com.example.wsy.spring.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceBySet {

    private MyDao dao;

    @Autowired
    public void setDao(MyDao dao) {
        this.dao = dao;

        System.out.println(dao);
    }

    public boolean save(boolean flag){
        return flag;
    }
}
