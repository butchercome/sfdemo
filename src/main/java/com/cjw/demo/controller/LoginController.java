package com.cjw.demo.controller;

import com.cjw.demo.entity.User;
import com.cjw.demo.entity.UserInfo;
import com.cjw.demo.service.LoginService;
import com.cjw.demo.service.RedisService;
import com.cjw.demo.service.RedisSprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/6/11.
 */
@Controller
@RequestMapping(value = {"/login"})
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    RedisService redisService;
    @Autowired
    RedisSprService redisSpringService;

    /**
     * mvc测试
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String loginCheck() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createUser(UserInfo user) {
        ModelAndView mav = new ModelAndView();
        loginService.createUser(user);
        mav.addObject("user", user);
        mav.setViewName("login/login");
        return mav;
    }

    @RequestMapping(value = "/desc", method = RequestMethod.GET)
    public String desc() {
        return "hs/desc";
    }


    /**
     * redis   start
     */
    @RequestMapping(value = "/userRedis", method = RequestMethod.GET)
    public String userRedis() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("sasasa");
        userInfo.setPassword("sasasasasa");
        redisService.userRedis(userInfo);
        return "index";
    }

    /**
     * spring   redis   start
     */
    @RequestMapping(value = "/userSpringRedis", method = RequestMethod.GET)
    public String userSpringRedis() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("sasasa");
        userInfo.setPassword("sasasasasa");
        URL url = null;
        User user = new User();
        user.setId("2121");
        user.setName("sssasa");
        redisSpringService.saveUser(user);
        redisSpringService.getUser(2121);
//        redisSpringService.set("springRedis", userInfo);
//        UserInfo xx = (UserInfo) redisSpringService.get("springRedis");
        return "index";
    }
    /** redis   end     */
    /**
     * cache start
     */
    @RequestMapping(value = "/cacheInfo", method = RequestMethod.GET)
    public String cacheInfo() {
        loginService.getCacheInfo();
        return "hs/desc";
    }

    @RequestMapping(value = "/destroyCache", method = RequestMethod.GET)
    public String destroyCache() {
        loginService.destroyCache();
        return "hs/desc";
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser() {
        String userId = "1";
        List<UserInfo> sss = loginService.getUser(userId);
        System.out.println("数据库中查到此用户号[" + userId + "]对应的用户名为[" + sss + "]");
        return "hs/desc";
    }

    public static void main(String[] args) {
        String  xx ="";
        int    len = xx.length();
        System.out.println(len);
    }


}
