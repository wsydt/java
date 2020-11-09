package com.example.wsy.classloader;

public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader("F:\\workspace-n\\Code\\bin");
        Class<?> aClass = loader.findClass("sort.Sorts");
        System.out.println(aClass);
    }
}
