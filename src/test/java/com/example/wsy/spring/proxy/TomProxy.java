package com.example.wsy.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TomProxy {

    private Object target;

    private AOP aop;

    public Person getProxy(Object t, AOP a){
        target = t;
        aop = a;
        return (Person) Proxy.newProxyInstance(TomProxy.class.getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("jump")) {
                    System.out.println("jump method : ");

                } else if (method.getName().equals("walk")) {
                    System.out.println("walk method : ");
                }
                aop.before();
                Object result = method.invoke(target, args);
                aop.after();
                //System.out.println(proxy);
                return result;
            }
        });
    }

}
