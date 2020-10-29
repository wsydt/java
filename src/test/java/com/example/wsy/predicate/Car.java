package com.example.wsy.predicate;

import lombok.Data;

@Data
public class Car {

    private String color;

    private String brand;

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    private Integer price;
}
