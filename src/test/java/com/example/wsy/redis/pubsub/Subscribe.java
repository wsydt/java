package com.example.wsy.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("pubsub")
public class Subscribe {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void setup(){
        redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            redisConnection.subscribe((message, bytes) -> System.out.println("accept message : " + message), "pubsub".getBytes());
            return null;
        });
    }

}
