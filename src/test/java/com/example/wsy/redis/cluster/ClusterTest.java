package com.example.wsy.redis.cluster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("cluster")
public class ClusterTest {

    @Autowired
    private ClusterService clusterService;

    @Test
    public void test01 (){
        clusterService.set("a","1");
        clusterService.set("b","1");
        clusterService.set("c","1");
        clusterService.set("d","1");
        clusterService.set("e","1");
        clusterService.set("f","1");
        clusterService.set("g","1");
    }

    @Test
    public void testFailover() throws InterruptedException {
        int i = 0;
        while (true) {
            clusterService.set("tdy", "love wsy : " + i ++);


            Thread.sleep(2000L);




        }
    }

}
