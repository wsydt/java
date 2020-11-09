package com.example.wsy.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOPAnnotation {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AOPConfig.class);
        IUserDao dao = applicationContext.getBean(IUserDao.class);
        dao.save();
        System.out.println(applicationContext.getBeanDefinitionCount());
    }
}
