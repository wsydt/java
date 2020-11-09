package com.example.wsy.pattern.bridge;

public class BridgePatternDemo {

    public static void main(String[] args) {
        Shape red = new Circle(10,6,5, new RedCircle());
        Shape green = new Circle(20,30,40, new GreenCircle());

        red.draw();
        green.draw();
    }

}
