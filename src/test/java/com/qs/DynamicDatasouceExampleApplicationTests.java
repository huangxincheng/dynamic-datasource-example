package com.qs;

import com.qs.domain.User;
import com.qs.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDatasouceExampleApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQyDataSource() {
        List<User> usersForh1 = userMapper.findUsersForh1();
        List<User> usersForh2 = userMapper.findUsersForh2();
        List<User> usersForDefault = userMapper.findUsers();
        System.out.println(usersForh1.size() + ":" + usersForh2.size() + ":" + usersForDefault.size());
    }

}
