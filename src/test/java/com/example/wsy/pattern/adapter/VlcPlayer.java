package com.example.wsy.pattern.adapter;

public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc file, name : " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // todo nothing
    }
}
