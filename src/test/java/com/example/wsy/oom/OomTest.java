package com.example.wsy.oom;

import java.util.ArrayList;

public class OomTest {

    static ArrayList<byte[]> space  = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        for (int i = 0; i < num; i ++) {
            byte[] b = new byte[1024 * 1024 * 64];
            space.add(b);
            Thread.sleep(3000);
        }
    }
}
