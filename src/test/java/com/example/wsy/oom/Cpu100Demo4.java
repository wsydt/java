package com.example.wsy.oom;

import java.util.Random;

public class Cpu100Demo4 {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i ++) {
            new Thread(()->{
                int x = 0;
                for (int j = 0; j < 10000; j ++) {
                    x = x + 1;
                    int time = new Random().nextInt(50);
                    System.out.println(Thread.currentThread().getName() + " : "+ x);
//                    try {
//                        Thread.sleep(time);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }, "cpu demo 4 : " + i).start();
            int random = new Random().nextInt(50);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
