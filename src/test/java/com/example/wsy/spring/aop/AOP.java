package com.example.wsy.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class AOP {

    @Pointcut("execution(* com.example.wsy.spring.aop.*.*(..))")
    public void pointcut(){

    }

    @AfterReturning("pointcut()")
    public void returning(){
        System.out.println("return aop returning");
    }

    @Before("pointcut()")
//    @Before("execution(* com.example.wsy.spring.aop.UserDao.*(..))")
    public void before() {
        System.out.println("AOP before method() ....");
    }

    @After("pointcut()")
//    @After("execution(* com.example.wsy.spring.aop.UserDao.*(..))")
    public void after() {
        System.out.println("AOP after method() ....");
    }

//    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp){
        Map<String, String> map = new HashMap<>();
        Object result = null;
        try {
            result = pjp.proceed();
            if (result instanceof List){
                List<String> list = (List) result;
                for (String s : list) {
                    map.put(s, s);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aop 转map");
        return map;
    }

}
