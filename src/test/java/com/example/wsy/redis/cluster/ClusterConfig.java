package com.example.wsy.redis.cluster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

@Configuration
@Profile("cluster")
public class ClusterConfig {

    @Bean
    public RedisConnectionFactory clusterConnectionFactory(){
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(Arrays.asList(
                "172.16.41.10:7381",
                "172.16.41.10:7382",
                "172.16.41.10:7383",
                "172.16.41.10:7384",
                "172.16.41.10:7385",
                "172.16.41.10:7386"
        ));
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, String> clusterRedis(RedisConnectionFactory clusterConnectionFactory){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(clusterConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
