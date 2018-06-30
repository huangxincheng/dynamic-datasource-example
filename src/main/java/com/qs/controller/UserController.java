package com.qs.controller;

import com.qs.domain.User;
import com.qs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Date:   2018/6/30 16:07
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/30 16:07         1.0.0
 * <p>
 * 春风十里不如你
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/h1")
    public List<User> findUserForh1() {
        return userMapper.findUsersForh1();
    }

    @RequestMapping("/h2")
    public List<User> findUserForh2() {
        return userMapper.findUsersForh2();
    }

    @RequestMapping("/default")
    public List<User> findUserForDefault() {
        return userMapper.findUsers();
    }
}
