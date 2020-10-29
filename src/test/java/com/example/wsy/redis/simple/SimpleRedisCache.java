package com.example.wsy.redis.simple;

import com.example.wsy.dto.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SimpleRedisCache {

    @Cacheable(cacheManager = "cacheManager", value = "cache-1", key = "#key")
    public User get(String key) {
        User user = new User();
        user.setId(Integer.parseInt(key));
        user.setAge(18);
        user.setName("jerry");
        user.setMail("aaa.163.com");
        System.out.println("read user from db");
        return user;
    }

    @CacheEvict(cacheManager = "cacheManager", value = "cache-1", key = "#key")
    public void delete(Integer key){
        System.out.println("delete data from db ....");
    }

    @CachePut(cacheManager = "cacheManager", value = "cache-1", key = "#user.id", condition = "#result ne null")
    public User update(User user) {
        System.out.println("update db of User.....");
        return user;
    }

}
