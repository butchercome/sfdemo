package com.cjw.demo.mapper;

import com.cjw.demo.entity.UserInfo;
import java.util.List;

/**
 * Created by 828471 on 2016/7/13.
 */

public interface UserInfoMapper {


    public void insertUserInfo(UserInfo user);

    List<UserInfo> queryUserInfo();
}
