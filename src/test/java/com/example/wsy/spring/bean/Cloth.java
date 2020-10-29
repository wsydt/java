package com.example.wsy.spring.bean;

public class Cloth {

    private String type;

    public Cloth(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "type='" + type + '\'' +
                '}';
    }
}
