package com.example.wsy.redis.sentinal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Profile("sentinel")
public class SentinelConfig {

    @Bean
    public RedisConnectionFactory sentinelConnectionFactory(){
        String host = "172.16.41.10";
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration().master("mymaster")
                .sentinel(host, 26380)
                .sentinel(host, 26381)
                .sentinel(host, 26382);
        return new LettuceConnectionFactory(redisSentinelConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> sentinelRedis(RedisConnectionFactory sentinelConnectionFactory){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(sentinelConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
