package com.example.wsy.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerializer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        TestObject testObject = new TestObject("100", "200");
        System.out.println(testObject);
        String filePath = "F://aa";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        outputStream.writeObject(testObject);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
        Object o = inputStream.readObject();
        System.out.println(o);
    }

    @AllArgsConstructor
    @Data
    public static class TestObject implements Serializable {
        private String x;
        private String y;
    }

}
