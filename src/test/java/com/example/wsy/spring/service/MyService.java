package com.example.wsy.spring.service;

import com.example.wsy.spring.dao.MyDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MyService {

    @Resource
    private MyDao dao;

    public MyService() {

    }

    public MyService(MyDao dao) {
        this.dao = dao;

        System.out.println(dao);
    }

    @Transactional
    public boolean save(boolean flag) {
        System.out.println("Server start work !!");
        dao.update();
        int a =  1 / 0;
        return dao.save(flag);
    }
}
