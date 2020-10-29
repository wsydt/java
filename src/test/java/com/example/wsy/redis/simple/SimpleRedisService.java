package com.example.wsy.redis.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setTest(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getTest(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }
}
