package com.example.wsy.oom;

public class DeadLockTest {

    static Object obj1 = new Object();
    static Object obj2 = new Object();
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Lock1());
        Thread thread2 = new Thread(new Lock2());
        thread1.start();
        thread2.start();
    }
}
class Lock1 implements Runnable {
    @Override
    public void run() {
        synchronized (DeadLockTest.obj1){
            System.out.println("Lock1 lock obj1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(DeadLockTest.obj2){
                System.out.println("Lock1 lock obj2");
            }
        }
    }
}

class Lock2 implements Runnable {
    @Override
    public void run() {
        synchronized (DeadLockTest.obj2){
            System.out.println("Lock2 lock obj2");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(DeadLockTest.obj1){
                System.out.println("Lock2 lock obj1");
            }
        }
    }
}
