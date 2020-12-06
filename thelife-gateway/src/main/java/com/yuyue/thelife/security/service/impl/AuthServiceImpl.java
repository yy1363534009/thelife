package com.yuyue.thelife.security.service.impl;

import com.yuyue.thelife.base.user.mapper.SysUserDetailMapper;
import com.yuyue.thelife.base.user.mapper.SysUserMapper;
import com.yuyue.thelife.base.user.moduls.SysUser;
import com.yuyue.thelife.base.user.moduls.SysUserDetail;
import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 21:28
 * @Description:
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserDetailMapper sysUserDetailMapper;

    @Override
    public JsonRestResponseVo register(RegisterRequest registerRequest) {
        SysUser sysUser = new SysUser();
        SysUserDetail sysUserDetail = new SysUserDetail();
        Integer userNum = sysUserMapper.insert(sysUser);
        Integer userDetailNum = sysUserDetailMapper.insert(sysUserDetail);
        return JsonRestResponseVo.Success();
    }

}
