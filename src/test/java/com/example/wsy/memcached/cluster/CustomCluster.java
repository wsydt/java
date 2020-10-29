package com.example.wsy.memcached.cluster;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCluster {

    public static MemcachedClient memcachedClient(String key) throws IOException {
        List<MemcachedClient> servers = new ArrayList<>();
        servers.add(new XMemcachedClient("172.16.41.10", 11211));
        servers.add(new XMemcachedClient("172.16.41.10", 11212));
        servers.add(new XMemcachedClient("172.16.41.10", 11213));

        // 计算 key 的hash值
        int hashcode = Math.abs(key.hashCode());
        // 计算key 对应的存放位置
        int slot = hashcode % servers.size();
        return servers.get(slot);
    }

}
