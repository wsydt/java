package com.example.wsy.pattern.bridge;

public class Circle extends Shape{

    private int x, y, radius;

    public Circle(int radius, int x, int y, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
