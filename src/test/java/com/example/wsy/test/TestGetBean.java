package com.example.wsy.test;

import com.example.wsy.service.UserService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;

@SpringBootTest
public class TestGetBean {

    @Resource
    ApplicationContext applicationContext;
    @Test
    public void test1(){
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
    }
}
