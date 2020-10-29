package com.example.wsy.redis.sentinal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("sentinel")
public class SentinelTest {

    @Autowired
    private RedisTemplate<String, String> sentinelRedis;

    @Test
    public void test01() throws InterruptedException {
        int i = 0;
        while (true) {
            sentinelRedis.opsForValue().set("sentinel", String.valueOf(i++));
            Thread.sleep(2000L);
        }
    }
}
