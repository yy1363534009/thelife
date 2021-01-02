package com.yuyue.thelife.security.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuyue.thelife.security.mapper.SysUserMapper;
import com.yuyue.thelife.security.model.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:22
 * @Description:
 */
@Service("sysUserDao")
public class SysUserDao {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 插入
     *
     * @param sysUser
     * @return
     */
    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public SysUser queryByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }

}
