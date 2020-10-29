package com.example.wsy.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("cluster")
public class ClusterService {

    @Autowired
    private RedisTemplate<String, String> clusterRedis;

    public void set(String key, String value) {
        clusterRedis.opsForValue().set(key, value);
    }

    public String get(String key) {
        return clusterRedis.opsForValue().get(key);
    }

}
