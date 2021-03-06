package com.cjw.demo.doc.ehcache.provider;

import com.cjw.demo.doc.entity.UserInfo;
import com.cjw.demo.doc.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 828471 on 2016/7/14.
 */
public class UserInfoCacheProvider extends BaseCacheDataProvider {
    @Autowired
    UserInfoMapper userInfoMapper;

    public Object getData(Object key) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (null != key && key instanceof String) {
            String keyTT = (String) key;
            if(keyTT.equals(CacheContents.USER_INFO)){
                List<UserInfo> userList=userInfoMapper.queryUserInfo();
                dataMap.put(CacheContents.USER_INFO, userList);
            }
        }
        return dataMap;
    }
}
