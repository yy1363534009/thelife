package com.yuyue.thelife.security.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    /**
     * 插入
     *
     * @param sysUserDetail
     * @return
     */
    public int insert(SysUserDetail sysUserDetail) {
        return sysUserDetailMapper.insert(sysUserDetail);
    }

    /**
     * 根据用户ID查询用户详情
     *
     * @param userId
     * @return
     */
    public SysUserDetail getByUserId(Long userId) {
        LambdaQueryWrapper<SysUserDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDetail::getUserId, userId);
        return sysUserDetailMapper.selectOne(queryWrapper);

    }

}
