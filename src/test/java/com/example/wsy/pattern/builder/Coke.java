package com.example.wsy.pattern.builder;

public class Coke extends Drink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 25.5f;
    }
}
