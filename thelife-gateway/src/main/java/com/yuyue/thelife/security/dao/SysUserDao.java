package com.yuyue.thelife.security.dao;

import com.yuyue.thelife.security.mapper.SysUserMapper;
import com.yuyue.thelife.security.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:22
 * @Description:
 */
@Service("sysUserDao")
public class SysUserDao {

    @Autowired
    private SysUserMapper sysUserMapper;

    public Integer insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

}
