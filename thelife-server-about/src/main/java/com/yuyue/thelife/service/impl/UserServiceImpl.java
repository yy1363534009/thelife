package com.yuyue.thelife.service.impl;

import com.yuyue.thelife.base.about.mapper.UserMapper;
import com.yuyue.thelife.base.about.moduls.User;
import com.yuyue.thelife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/3 22:20
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

}
