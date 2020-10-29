package com.example.wsy.redis.replication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("replication")
public class ReplicationService {

    @Autowired
    private RedisTemplate<String, String> replicationStringRedis;

    public void set(String key, String value){
        replicationStringRedis.opsForValue().set(key, value);
    }

    public String get(String key) {
        return replicationStringRedis.opsForValue().get(key);
    }


}
