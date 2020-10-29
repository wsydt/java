package com.example.wsy.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class Tom  implements Person{

    @Override
    public void jump(String many) {
        System.out.println("jump jump jump : " + many);
    }

    @Override
    public void walk(Integer steps) {
        System.out.println("walk walk walk : " + steps);
    }
}
