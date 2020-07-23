package com.example.wsy.aqs;

public class WsyLock {

    private Sync sync = new Sync();

    private class Sync extends Aqs{
        @Override
        boolean tryAcquire() {
            return getOwner().compareAndSet(null, Thread.currentThread());
        }

        @Override
        boolean tryRelease() {
            return getOwner().compareAndSet(Thread.currentThread(), null);
        }
    }

    public void lock(){
        sync.acquire();
    }

    public void unlock(){
        sync.release();
    }

}
