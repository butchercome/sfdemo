package com.cjw.demo.doc.service;

import com.cjw.demo.doc.ehcache.CacheManager;
import com.cjw.demo.doc.ehcache.provider.CacheContents;
import com.cjw.demo.doc.entity.UserInfo;
import com.cjw.demo.doc.mapper.UserInfoMapper;
import com.cjw.demo.doc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/6.
 */
@Component
public class LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void createUser(UserInfo user) {
        userMapper.createUser(user);
        userInfoMapper.insertUserInfo(user);

    }

    @Cacheable(value = "myCache", key = "#userId")
    public List<UserInfo> getUser(String userId) {
        List<UserInfo> userInfos;
        userInfos = userInfoMapper.queryUserInfo();
        return userInfos;
    }


    public void getCacheInfo() {
        String key = CacheContents.USER_INFO;
        Map<String, List<UserInfo>> provinceMap = (Map<String, List<UserInfo>>) CacheManager.getInstance()
                .getCache(CacheContents.CACHE_USER_INFO).getData(CacheContents.USER_INFO);
        List<UserInfo> info = provinceMap.get(key);

    }

    public void destroyCache() {
        String key = CacheContents.USER_INFO;
        CacheManager.getInstance().getCache(CacheContents.CACHE_USER_INFO).removeData(CacheContents.USER_INFO);

    }
}
