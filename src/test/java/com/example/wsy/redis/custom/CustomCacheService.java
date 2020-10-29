package com.example.wsy.redis.custom;

import com.example.wsy.dto.User;
import com.example.wsy.redis.custom.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("custom")
public class CustomCacheService {

    @Cache(key = "#key")
    public User findUserById(String key) {
        User user = new User();
        user.setName("dog");
        user.setId(Integer.parseInt(key));
        user.setAge(18);
        user.setMail("wsydt2020@gmail.com");
        return user;
    }

}
