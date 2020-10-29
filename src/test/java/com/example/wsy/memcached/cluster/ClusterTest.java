package com.example.wsy.memcached.cluster;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:memcached.xml")
@ActiveProfiles("cluster")
public class ClusterTest {

    @Autowired
    private ClusterService service;

    @Test
    public void test01() throws InterruptedException, MemcachedException, TimeoutException {
        service.findUser("Tom");
        service.findUser("Jerry");
        service.findUser("Sky");
        service.findUser("Daisy");
        service.findUser("Snow");
        service.findUser("Bran");
        service.findUser("Walt");
        service.findUser("Ross");
        service.findUser("Joey");
        service.findUser("Penny");
        service.findUser("Fish");
        service.findUser("Hank");
        service.findUser("Chopper");
    }


    @Autowired
    private CustomService customService;

    @Test
    public void test02() throws InterruptedException, TimeoutException, MemcachedException, IOException {
        customService.findUser("Tom");
        customService.findUser("Jerry");
        customService.findUser("Sky");
        customService.findUser("Daisy");
        customService.findUser("Snow");
        customService.findUser("Bran");
        customService.findUser("Walt");
        customService.findUser("Ross");
        customService.findUser("Joey");
        customService.findUser("Penny");
        customService.findUser("Fish");
        customService.findUser("Hank");
        customService.findUser("Chopper");
    }

    @Test
    public void twemproxyClusterTest() throws IOException, InterruptedException, MemcachedException, TimeoutException {
        // 和代理直接连接即可，客户端无感知
        XMemcachedClient xMemcachedClient = new XMemcachedClient("172.16.41.10", 22125);
        xMemcachedClient.set("uid10001", 0, "{uname:tony,age:18}");
        xMemcachedClient.set("uid10003", 0, "{uname:jack,age:17}");
        xMemcachedClient.set("uid10002", 0, "{uname:mengmeng,age:19}");
    }

}
