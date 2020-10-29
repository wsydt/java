package com.example.wsy.spring.factory;

import com.example.wsy.spring.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBeanFactoryTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application2.xml");
        User user = (User) context.getBean("user");
        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user2");
        User user3 = (User) context.getBean("user3");

        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
