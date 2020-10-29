package com.example.wsy.pattern.builder;

public class Pepsi extends Drink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 25.5f;
    }
}
