package com.example.wsy.redis.replication;

import io.lettuce.core.ReadFrom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Profile("replication")
public class ReplicationConfig {

    @Bean
    public RedisConnectionFactory replicationConnectionFactory(){
        //启用读写分离

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder().readFrom(ReadFrom.REPLICA).build();
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("172.16.41.10", 6380);
        return new LettuceConnectionFactory(redisConfig, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> replicationStringRedis(RedisConnectionFactory replicationConnectionFactory){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(replicationConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
    @Bean
    public RedisTemplate<String, Object> replicationRedis(RedisConnectionFactory replicationConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(replicationConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }
}
