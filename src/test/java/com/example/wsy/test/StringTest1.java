package com.example.wsy.test;

import java.nio.charset.Charset;

public class StringTest1 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println(Charset.defaultCharset());
        String aa = "最向往的地方";
        byte[] bytes = aa.getBytes();
        System.out.println(bytes);

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
