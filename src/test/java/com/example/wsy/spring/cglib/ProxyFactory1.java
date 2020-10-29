package com.example.wsy.spring.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory1{

    public Object getProxyInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("AOP start ...");
                Object result = method.invoke(target, args);
                System.out.println("AOP end ...");
                return result;
            }
        });
    }
}
