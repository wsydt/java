package com.example.wsy.pattern.adapter;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "最向往的地方");
        audioPlayer.play("vlc", "不懂这种格式的文件");
        audioPlayer.play("mp4", "致命复活");
        audioPlayer.play("flac", "爱你不是两三天");
    }
}
