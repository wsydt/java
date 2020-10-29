package com.example.wsy.oom;

public class ThreadWaitTest {
    public static void main(String[] args) {
        new ThreadWaitTest().test();
    }

    private void test() {
        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println("wait thread 进入等待...");
                synchronized(this) {
                    this.wait();
                }
                System.out.println("wait thread 被唤醒...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"wait thread").start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("唤醒等待线程 ...");
                synchronized(this) {
                    this.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"notify thread").start();
    }
}
