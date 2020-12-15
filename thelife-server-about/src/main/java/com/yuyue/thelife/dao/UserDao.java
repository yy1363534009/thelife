package com.yuyue.thelife.dao;

import com.yuyue.thelife.mapper.UserMapper;
import com.yuyue.thelife.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:06
 * @Description:
 */
@Service("userDao")
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public Integer insert(User user) {
        return userMapper.insert(user);
    }

}
