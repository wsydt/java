package com.example.wsy.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class AOP {

    public void before() {
        System.out.println("AOP before method() ....");
    }

    public void after() {
        System.out.println("AOP after method() ....");
    }
}
