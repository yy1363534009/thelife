package com.yuyue.thelife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyue.thelife.mapper.UserMapper;
import com.yuyue.thelife.model.User;
import com.yuyue.thelife.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/3 22:20
 * @Description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean insert(User user) {
        return super.save(user);
    }

}
