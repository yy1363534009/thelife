package com.yuyue.thelife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyue.thelife.UserDao;
import com.yuyue.thelife.mapper.UserMapper;
import com.yuyue.thelife.model.User;
import com.yuyue.thelife.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yuyue
 * @Date: 2020/12/3 22:20
 * @Description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public Integer insert(User user) {
        return userDao.insert(user);
    }

}
