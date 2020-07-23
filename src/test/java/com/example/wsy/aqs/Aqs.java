package com.example.wsy.aqs;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public abstract class Aqs {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    private AtomicInteger state = new AtomicInteger();

    public AtomicInteger getState() {
        return state;
    }

    public void setState(AtomicInteger state) {
        this.state = state;
    }

    void acquire() {
        Thread thread = Thread.currentThread();
        boolean add =true;
        while(!tryAcquire()) {
            if (add) {
                waiters.offer(thread);
                add = false;
            }
            LockSupport.park();
        }
        waiters.remove(thread);
    }

    boolean tryAcquire(){
        throw new UnsupportedOperationException();
    }

    void release(){
        if(tryRelease()) {
            Iterator<Thread> iterator = waiters.iterator();
            while(iterator.hasNext()) {
                Thread thread = iterator.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public AtomicReference<Thread> getOwner() {
        return owner;
    }

    public void setOwner(AtomicReference<Thread> owner) {
        this.owner = owner;
    }

    public LinkedBlockingQueue<Thread> getWaiters() {
        return waiters;
    }

    public void setWaiters(LinkedBlockingQueue<Thread> waiters) {
        this.waiters = waiters;
    }

    boolean tryRelease(){
        throw new UnsupportedOperationException();
    }

}
