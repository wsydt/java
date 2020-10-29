package com.example.wsy.memcached.example;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:memcached.xml")
public class ExtTest {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void extTest() throws IOException {
        for (int j = 0; j < 1; j++ ) {
            new Thread(() -> {
                String content = "";
                for (int i = 0; i < 10240; i++) {
                    try {
                        memcachedClient.set(i+"", 36000, content);
                        System.out.println("send success !");
                    } catch (InterruptedException | MemcachedException | TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.in.read();
    }

}
