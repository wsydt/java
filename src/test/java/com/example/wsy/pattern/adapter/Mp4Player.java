package com.example.wsy.pattern.adapter;

public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // todo nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("playing mp4.file, fileName : " + fileName);
    }
}
