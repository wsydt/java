package com.example.wsy.test;

public class StringTest1 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        StringTest1 test1 = new StringTest1();
        Object object = test1.getObject(B.class);
        System.out.println(object);
    }
    static int i = 1;

    static  {
        i = 0;
        System.out.println(i);
    }

    public <T> Object getObject(Class<? super B> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static class A {

    }

    public static class B extends A {

    }



}
