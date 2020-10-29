package com.example.wsy.redis.pubsub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("pubsub")
public class PubSubTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test01() throws InterruptedException {
        System.out.println("5 seconds send a message ...");
        Thread.sleep(5000L);
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            redisConnection.publish("pubsub".getBytes(), "message sony wh-1000xm4".getBytes());
            return null;
        });
    }

    @Test
    public void test02() throws InterruptedException {
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
           redisConnection.subscribe(((message, bytes) -> {
               System.out.println("accept message , use redisTemplate accept data : " + message);
           }), "__keyevent@0__:del".getBytes());
           return null;
        });

        redisTemplate.opsForValue().set("invoke", "eeeeeeeeeeeeeeeeeee");
        Thread.sleep(1000L);
        redisTemplate.delete("invoke");
        System.out.println("delete key success");
    }

}
