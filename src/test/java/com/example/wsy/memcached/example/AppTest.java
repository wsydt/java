package com.example.wsy.memcached.example;

import com.example.wsy.memcached.pojo.User;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:memcached.xml")
@ActiveProfiles("single")
public class AppTest {

    @Autowired
    private AppService service;

    @Test
    public void test01(){
        String key = "mouse";
        try {
            User user = service.findUser(key);
            System.out.println(user);
        } catch (InterruptedException | MemcachedException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
