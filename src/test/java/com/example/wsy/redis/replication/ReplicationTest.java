package com.example.wsy.redis.replication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("replication")
public class ReplicationTest {

    @Autowired
    private ReplicationService service;

    String key ="agent";
    String value= "Jerry";

    @Test
    public void setTest(){
        service.set(key, value);
    }

    @Test
    public void getTest(){
        String value = service.get(key);
        System.out.println(" get value : " + value);
    }

}
