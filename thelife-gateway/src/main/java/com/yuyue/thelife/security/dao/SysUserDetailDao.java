package com.yuyue.thelife.security.dao;

import com.yuyue.thelife.security.mapper.SysUserDetailMapper;
import com.yuyue.thelife.security.model.SysUserDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yuyue
 * @Date: 2020/12/8 23:23
 * @Description:
 */
@Service("sysUserDetailDao")
public class SysUserDetailDao {

    @Resource
    private SysUserDetailMapper sysUserDetailMapper;

    public int insert(SysUserDetail sysUserDetail) {
        return sysUserDetailMapper.insert(sysUserDetail);
    }

}
