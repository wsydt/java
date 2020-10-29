package com.example.wsy.redis.simple;

import com.example.wsy.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
//@ActiveProfiles("simple")
public class CacheTest {

    @Autowired
    private SimpleRedisCache cache;

    @Test
    public void get(){
        User user = cache.get("123456");
        System.out.println(user);
    }

    @Test
    public void update(){
        User u = new User();
        u.setId(987654);
        u.setName("tom");
        u.setAge(10);
        u.setMail("ttt@163.com");
        User user = cache.update(u);
        System.out.println(user);
    }

    @Test
    public void delete(){
        cache.delete(123456);
    }
}
