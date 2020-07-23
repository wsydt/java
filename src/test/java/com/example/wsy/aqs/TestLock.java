package com.example.wsy.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class TestLock {

    private int i = 0;

    private WsyLock lock = new WsyLock();

    public void add(){
        lock.lock();
        try {
            i++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String [] args) {
        int num = 1000;
        long time = System.currentTimeMillis();
        WsyCountDownLatch latch = new WsyCountDownLatch(num);
        TestLock testLock = new TestLock();
        for(int i = 0;i < num;i ++) {
            new Thread(()->{
                for (int j = 0;j < 10;j ++) {
                    testLock.add();
                }
                latch.countDown();
        }).start();
        }
            latch.await();
        System.out.println("time : " + (System.currentTimeMillis() - time));
        System.out.println(testLock.i);

    }
}
