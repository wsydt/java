package com.example.wsy.memcached.example;

import com.example.wsy.memcached.pojo.User;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
@Profile("single")
public class AppService {

    @Autowired
    private MemcachedClient memcachedClient;

    public User findUser(String key) throws InterruptedException, MemcachedException, TimeoutException {
        User user = null;
        //todo 判定缓存中是否存在
        user = memcachedClient.get(key);
        if (user != null) {
            System.out.println("get user info from memcached : " + user);
            return user;
        }
        //todo 如果缓存不存在则读取数据库的值
        user = new User(key, "Jerry");
        System.out.println("get data from database : " + user);
        // TODO 同步存储value到 memcached, 缓存超时时间为一个小时, 3600秒
        memcachedClient.set(key, 3600, user);
        return user;
    }

}
