package com.example.wsy.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayTest {
    
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        List<Object> synchronizedList = Collections.synchronizedList(list);
    }
}
