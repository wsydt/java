package com.example.wsy.memcached.cluster;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile("cluster")
public class ClusterConfig {

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        String server = "172.16.41.10:11211 172.16.41.10:11212 172.16.41.10:11213";
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(server));
        // 默认的客户端计算就是 key 的哈希值模以 连接数
        // KetamaMemcachedSessionLocator  一致性hash 算法
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());
        return builder.build();
    }

}
