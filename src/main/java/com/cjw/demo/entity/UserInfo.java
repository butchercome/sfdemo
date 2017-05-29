package com.cjw.demo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/6.
 */
public class UserInfo implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
