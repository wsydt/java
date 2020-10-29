package com.example.wsy.redis.pubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Profile("pubsub")
public class SubscribeBySpring {

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        MessageListener messageListener = new MessageListener(){

            @Override
            public void onMessage(Message message, byte[] bytes) {
                System.out.println("use spring ioc container accept message : " + message);
            }
        };
        container.addMessageListener(messageListener, new ChannelTopic("__keyevent@0__:del"));
        return container;
    }
}
