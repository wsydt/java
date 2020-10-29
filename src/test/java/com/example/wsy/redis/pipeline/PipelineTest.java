package com.example.wsy.redis.pipeline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class PipelineTest {

    @Autowired
    private RedisTemplate<String, Object> redis;

    @Test
    public void test01() {
        long time = System.currentTimeMillis();
        int num  = 100000;

        for (int i = 0; i < num; i ++) {
            redis.opsForList().rightPush("queue-1", i);
        }

        long costTime1 = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        redis.executePipelined((RedisCallback<String>) connection -> {
            for (int i = 0; i < num; i++) {
                connection.rPush("queue-2".getBytes(), String.valueOf(i).getBytes());
            }
            return null;
        });

        long costTime2 = System.currentTimeMillis() - time;


        System.out.println("operation complete, " +redis.opsForList().size("queue-1"));
        System.out.println("cost time : " + costTime1);

        System.out.println("operation complete, " +redis.opsForList().size("queue-2"));
        System.out.println("cost time : " + costTime2);

    }
}
