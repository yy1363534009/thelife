package com.yuyue.thelife.service.impl;

import com.yuyue.thelife.dao.UserDao;
import com.yuyue.thelife.model.User;
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
    private UserDao userDao;

    @Override
    public Integer insert(User user) {
        return userDao.insert(user);
    }

}
