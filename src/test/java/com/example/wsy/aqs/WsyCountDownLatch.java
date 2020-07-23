package com.example.wsy.aqs;

public class WsyCountDownLatch {

    private Sync sync = new Sync();

    public WsyCountDownLatch(int count) {
        sync.getState().set(count);
    }

    private class Sync extends Aqs {
        @Override
        boolean tryAcquire() {
            return getState().get() <= 0;
        }

        @Override
        boolean tryRelease() {
            return getState().decrementAndGet() <= 0;
//            return false;
        }
    }

    public void countDown(){
        sync.release();
    }

    public void await(){
        sync.acquire();
    }

}
