package com.yuyue.thelife.dao;

import com.yuyue.thelife.mapper.UserMapper;
import com.yuyue.thelife.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:06
 * @Description:
 */
@Service("userDao")
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public Integer insert(User user) {
        return userMapper.insert(user);
    }

}
