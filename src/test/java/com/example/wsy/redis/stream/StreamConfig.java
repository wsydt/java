package com.example.wsy.redis.stream;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stream")
public class StreamConfig {

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create("redis://172.16.41.10:6379");
    }

    @Bean
    public RedisCommands<String, String> redisCommands(RedisClient redisClient){
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        return connect.sync();
    }
}