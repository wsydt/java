package com.example.wsy.test;

public class GetIp {

    public static void main(String[] args) {
        String address = "172.16.41.10";
        String[] p = address.split("\\.");
//        System.out.println(p.length);
        for (String str : p) {
            int part = Integer.parseInt(str);
            System.out.print(Integer.toBinaryString(part) + ".");
        }
    }
}
