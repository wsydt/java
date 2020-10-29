package com.example.wsy.spring.bean;

import java.util.List;

public class User {

    private String name;

    private int age;

    private List<Object> phones;

    private List<Object> tags;

    private List<Cloth> cloth;

    public User(){

    }

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public User(String name, int age, List<Object> phones) {
        this(name, age);
        this.phones = phones;
    }

    //defaule name, age
    public User(List<Object> phones) {
        this("wsy love tdy", 3);
        this.phones = phones;
    }

    public User(List<Object> phones, List<Cloth> cloth) {
        this(phones);
        this.cloth = cloth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + phones +
                ", tags=" + tags +
                '}';
    }
}
