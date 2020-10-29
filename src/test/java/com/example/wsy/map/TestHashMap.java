package com.example.wsy.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestHashMap {

    public static void main(String [] args) {
        WsyHashMap<String, Integer> hashMap = new WsyHashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(i+"", i);
        }
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        System.out.println(entrySet);
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }
    }

}
