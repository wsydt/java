package com.example.wsy.service.impl;

import com.example.wsy.dao.TestDao;
import com.example.wsy.dto.User;
import com.example.wsy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao test;

    @Override
    public User test01(String id) {
        System.out.println(test);
        System.out.println(id);
        User user = test.test01(id);
        System.out.println(user);
        return user;
    }
}
