package com.cjw.demo.service.interfaces;

import com.cjw.demo.entity.User;

/**
 * Created by 828471 on 2016/7/22.
 */
public interface IRedisSprService {
    User getUser(long  id);

}
