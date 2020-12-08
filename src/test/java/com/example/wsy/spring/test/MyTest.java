package com.example.wsy.spring.test;

import com.example.wsy.spring.configuration.MyConfiguration;
import com.example.wsy.spring.controller.MyController;
import com.example.wsy.spring.service.MyService;
import com.example.wsy.spring.service.MyServiceBySet;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes= MyConfiguration.class)
public class MyTest {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application3.xml");
////        MyController controller = (MyController) context.getBean("myController");
//        MyServiceBySet controller = (MyServiceBySet) context.getBean("setService2");
//        System.out.println(controller);
//        System.out.println(controller.save(false));
//
//        System.out.println("---------------------------------- ---------------------- ---------------------");
//        System.out.println(controller.save(true));
        test();
    }

//    @Test
    public static void  test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        MyController controller = (MyController) context.getBean("myController");
        System.out.println(controller);
//        System.out.println(controller.save(false));
//
//        System.out.println("---------------------------------- ---------------------- ---------------------");
//        System.out.println(controller.save(true));
    }
}
