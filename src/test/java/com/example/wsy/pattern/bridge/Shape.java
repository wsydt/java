package com.example.wsy.pattern.bridge;

public abstract class Shape {

    protected DrawAPI drawAPI;

    public Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    abstract void draw();

}
