package com.example.wsy.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestAOP {
    public static void main(String[] args) {
//        aop();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        IUserDao dao = (IUserDao) applicationContext.getBean("userDao");
        System.out.println(dao.getClass());
        dao.save();

        TestDao test = (TestDao) applicationContext.getBean("testDao");
        System.out.println(test.getClass());
        Object user = test.getUser();
        System.out.println(user);

    }

    private static void aop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        TestDao test = (TestDao) applicationContext.getBean("testDao");
        System.out.println(test.getClass());
        test.save();
        IUserDao user = (IUserDao) applicationContext.getBean("userDao");
        System.out.println(user.getClass());
        user.save();
    }
}
