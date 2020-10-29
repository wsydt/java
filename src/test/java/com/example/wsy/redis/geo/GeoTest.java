package com.example.wsy.redis.geo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@ActiveProfiles("geo")
public class GeoTest {

    @Autowired
    private GeoService service;

    @Test
    public void test01(){
        //模拟上报位置
        service.addPosition("tom", new Point(116.405285,39.904989));
        service.addPosition("jerry", new Point(116.405265,39.904969));
        service.addPosition("tdy", new Point(116.405215,39.904999));
        service.addPosition("wsy", new Point(116.405205,39.904909));

        //查找tdy 附近的人
        GeoResults<RedisGeoCommands.GeoLocation<String>> result = service.nearby(new Point(116.405215,39.904999));
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> user : result) {
            System.out.println(user);
        }
    }

}
