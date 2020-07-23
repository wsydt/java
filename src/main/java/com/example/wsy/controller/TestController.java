package com.example.wsy.controller;

import com.example.wsy.dto.User;
import com.example.wsy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test01")
    public User test01 (String id){
        User user = null;
        System.out.println(id);
        try {
            user = testService.test01(id);
        } catch (Exception e) {

        }
        return user;
    }


}
