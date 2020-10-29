package com.example.wsy.redis.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("geo")
public class GeoService {

    @Autowired
    private RedisTemplate<String, String> redisString;

    //上报附近的人
    public void addPosition(String user, Point point) {
        redisString.opsForGeo().add("user-geo", point, user);
    }

    //查找附近的人
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearby(Point point) {
        //半径100米
        Distance distance = new Distance(100, RedisGeoCommands.DistanceUnit.METERS);
        Circle circle = new Circle(point, distance);

        //限制查找5个人
        RedisCommands.GeoRadiusCommandArgs args = RedisCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> result = redisString.opsForGeo().radius("user-geo",circle , args);
        return result;
    }

}
