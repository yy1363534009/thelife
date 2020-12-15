package com.yuyue.thelife.security.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    public SysUser queryByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }

}
