package com.yuyue.thelife.security.dao;

import com.yuyue.thelife.security.mapper.SysUserDetailMapper;
import com.yuyue.thelife.security.model.SysUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:23
 * @Description:
 */
@Service("sysUserDetailDao")
public class SysUserDetailDao {

    @Autowired
    private SysUserDetailMapper sysUserDetailMapper;

    public Integer insert(SysUserDetail sysUserDetail) {
        return sysUserDetailMapper.insert(sysUserDetail);
    }

}
