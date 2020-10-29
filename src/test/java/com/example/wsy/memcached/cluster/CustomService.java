package com.example.wsy.memcached.cluster;

import com.example.wsy.memcached.pojo.User;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Profile("cluster")
public class CustomService {

    public User findUser(String key) throws InterruptedException, MemcachedException, TimeoutException, IOException {

        MemcachedClient memcachedClient = CustomCluster.memcachedClient(key);

        User user = null;

        user = memcachedClient.get(key);
        if (user != null) {
            System.out.println("get value from memcached : " + user);
            return user;
        }

        user = new User(key, "Tom");
        System.out.println("get data from database : " + user);

        //将数据存到 memcached 设置过期时间3600秒= 一小时
        memcachedClient.set(key, 3600, user);

        return user;
    }

}
