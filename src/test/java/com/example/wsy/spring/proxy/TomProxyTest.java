package com.example.wsy.spring.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TomProxyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application4.xml");
        Person person = (Person) applicationContext.getBean("proxy1");
        person.jump("go go go !!!");
        person.walk(4525);

    }
}
