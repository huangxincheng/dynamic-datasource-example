package com.qs.mapper;

import com.qs.domain.User;
import com.qy.dynamic.spring.boot.autoconfigure.annotation.QySource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Date:   2018/6/30 16:00
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/30 16:00         1.0.0
 * <p>
 * 春风十里不如你
 **/
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findUsers();

    @Select("select * from user")
    @QySource(source = "h1")
    List<User> findUsersForh1();

    @Select("select * from user")
    @QySource(source = "h2")
    List<User> findUsersForh2();

    @Insert("insert into user(username) values(#{username})")
    int insert(User user);

    @Insert("insert into user(username) values(#{username})")
    @QySource(source = "h1")
    int inserth1(User user);

    @Insert("insert into user(username) values(#{username})")
    @QySource(source = "h2")
    int inserth2(User user);
}
