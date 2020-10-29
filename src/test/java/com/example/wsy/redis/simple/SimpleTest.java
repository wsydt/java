package com.example.wsy.redis.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
//@ActiveProfiles("simple")
public class SimpleTest {

    @Autowired
    private SimpleRedisService simpleRedisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisString;

    @Test
    public void setTest() {
        System.out.println(simpleRedisService);
        simpleRedisService.setTest("test01", "tdy");
    }

    @Test
    public void getTest(){
        Object value = simpleRedisService.getTest("tdy");
        System.out.println(value);
    }

    @Test
    public void setStringTest(){
        String key = "tdy";
        String value = "love wsy";
//        stringRedisTemplate.opsForValue().set(key, value);
        redisString.opsForValue().set(key, value);
    }

    @Test
    public void getStringTest(){
        String value = stringRedisTemplate.opsForValue().get("tdy");
        System.out.println(value);
    }

}
