package com.example.wsy.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest1 {

    public static void main(String [] args) {
        List<String> list = null;
//        list.add("1");

        Optional<List<String>> list1 = Optional.ofNullable(list);
        System.out.println(list1.get());
    }
}
