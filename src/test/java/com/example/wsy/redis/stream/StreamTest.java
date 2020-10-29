package com.example.wsy.redis.stream;

import io.lettuce.core.Consumer;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("stream")
public class StreamTest {

    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Test
    public void producer(){
        redisCommands.xadd("stream_send", "sendId", "1002", "content", "please reply after you got message,do you repeat?");
    }

    @Test
    public void consumer(){
        List<StreamMessage<String, String>> stream_send = redisCommands.xread(XReadArgs.StreamOffset.from("stream_send", "0"));
        for (StreamMessage<String, String> message : stream_send) {
            System.out.println(message);
        }
    }

    @Test
    public void createGroup() {
        //创建分组
        redisCommands.xgroupCreate(XReadArgs.StreamOffset.from("stream_send", "0"), "group_1");
    }

    @Test
    public void consumerGroup() {
        //按组消费
        List<StreamMessage<String, String>> xreadgroup = redisCommands.xreadgroup(Consumer.from("group_1", "consumer_1"), XReadArgs.StreamOffset.lastConsumed("stream_send"));
        for (StreamMessage<String, String> message : xreadgroup) {
            System.out.println(message);
            redisCommands.xack("stream_send", "group_1", message.getId());
        }
    }
}
