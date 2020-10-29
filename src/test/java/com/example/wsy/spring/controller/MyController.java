package com.example.wsy.spring.controller;

import com.example.wsy.spring.service.MyService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class MyController {

    @Resource
    private MyService service;

    public boolean save(boolean flag){
        return service.save(flag);
    }

}
