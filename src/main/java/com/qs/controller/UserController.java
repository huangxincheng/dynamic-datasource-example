package com.qs.controller;

import com.qs.domain.User;
import com.qs.mapper.UserMapper;
import com.qy.dynamic.spring.boot.autoconfigure.QyDataSource;
import com.qy.dynamic.spring.boot.autoconfigure.annotation.QySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    @RequestMapping("/h1/add/{username}")
    @QySource(source = "h1")
    public int addUserForh1(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.insert(user);
    }

    @RequestMapping("/h2/add/{username}")
    @QySource(source = "h2")
    public int addUserForh2(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.insert(user);
    }

    @RequestMapping("/default/add/{username}")
    public int addUserForDefault(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.insert(user);
    }

    @RequestMapping("/transactional")
    @Transactional
    public int transactional() {
        QyDataSource.putSourceKey("h1");
        User user1 = new User();
        user1.setUsername("111");
        userMapper.insert(user1);

        QyDataSource.putSourceKey("h2");
        User user2 = new User();
        user2.setUsername("222");
        userMapper.insert(user2);
        return 2;
    }

    @RequestMapping("/transactional2")
    @Transactional
    public int transactional2() {
        User user1 = new User();
        user1.setUsername("333");
        userMapper.inserth1(user1);

        User user2 = new User();
        user2.setUsername("444");
        userMapper.inserth2(user2);
        return 2;
    }

    public static void main(String[] args) {
        int countSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(countSize);
        Executors.newFixedThreadPool(countSize).execute(() -> {
            for (int i = 0; i < countSize; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread() + " 111");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                        System.out.println(countDownLatch.getCount());
                    }
            }
        });
        try {
//            countDownLatch.await(1, TimeUnit.SECONDS);
            countDownLatch.await(300, TimeUnit.SECONDS);
            System.out.println(countDownLatch.getCount());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + "处理超时");
        }
        System.out.println(Thread.currentThread() + "ok");
    }

}
