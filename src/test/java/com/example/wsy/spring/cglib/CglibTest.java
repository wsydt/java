package com.example.wsy.spring.cglib;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CglibTest {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new UserDao());
        UserDao dao = (UserDao) factory.getProxyInstance();
        dao.save();
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        System.out.println(applicationContext);
//        UserDao dao = (UserDao) applicationContext.getBean("user");
        System.out.println(dao);
    }
}
