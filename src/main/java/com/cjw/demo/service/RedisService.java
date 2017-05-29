package com.cjw.demo.service;

import com.cjw.demo.entity.UserInfo;
import com.cjw.demo.redis.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by 828471 on 2016/7/15.
 */
@Service
public class RedisService {

    //  切换配置,因此配置已切换暂时注释
    @Autowired
    RedisTemplate redisTemplate;

    public void userRedis(UserInfo userInfo) {
        String ss = "wwwwwwwwwwwww22wwwwwwww";
        redisTemplate.del("44");
        String yy = redisTemplate.get("46");
        redisTemplate.set("46", ss);
        String mm = redisTemplate.get("46");
        redisTemplate.del("46");
        redisTemplate.disconnect();
    }


}
