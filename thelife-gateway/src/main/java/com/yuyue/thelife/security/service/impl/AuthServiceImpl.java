package com.yuyue.thelife.security.service.impl;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.dao.SysUserDao;
import com.yuyue.thelife.security.dao.SysUserDetailDao;
import com.yuyue.thelife.security.model.SysUser;
import com.yuyue.thelife.security.model.SysUserDetail;
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
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserDetailDao sysUserDetailDao;

    @Override
    public JsonRestResponseVo register(RegisterRequest registerRequest) {
        SysUser sysUser = new SysUser();
        SysUserDetail sysUserDetail = new SysUserDetail();
        Integer userNum = sysUserDao.insert(sysUser);
        Integer userDetailNum = sysUserDetailDao.insert(sysUserDetail);
        return JsonRestResponseVo.Success();
    }

}
