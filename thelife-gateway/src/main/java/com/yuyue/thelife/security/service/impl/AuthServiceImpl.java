package com.yuyue.thelife.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.yuyue.thelife.exception.hanlder.TheLifeException;
import com.yuyue.thelife.security.dao.SysUserDao;
import com.yuyue.thelife.security.dao.SysUserDetailDao;
import com.yuyue.thelife.security.dto.JwtUser;
import com.yuyue.thelife.security.enums.LoginMethod;
import com.yuyue.thelife.security.model.SysUser;
import com.yuyue.thelife.security.model.SysUserDetail;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 17:13
 * @Description:
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserDetailDao sysUserDetailDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = sysUserDao.queryByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户或密码不正确");
        }
        if (user.getIsEnabled() != 1) {
            throw new TheLifeException("账号未激活！");
        }
        JwtUser jwtUser = new JwtUser(user.getUsername(), user.getPassword(), user.getUsername(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"), user.getIsEnabled() == 1 ? true : false);
        return jwtUser;

    }

    @Override
    public void register(RegisterRequest registerRequest) {
        logger.info("注册开始-当前用户：RegisterRequest={}", JSON.toJSONString(registerRequest));
        SysUser user = sysUserDao.queryByUsername(registerRequest.getUsername());
        if (user != null) {
            throw new TheLifeException("当前用户" + registerRequest.getUsername() + "已存在");
        }
        Date now = new Date();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerRequest.getUsername());
        if (registerRequest.getLoginMethod() == LoginMethod.WECHAT) {
            // 去用户名表中查看openId是否注册，如果未注册就自动注册，因为微信小程序password不存，就指定。
            String password = "wechatpassword";
            sysUser.setPassword(passwordEncoder.encode(password));
        } else {
            sysUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }
        sysUser.setLoginMethod(registerRequest.getLoginMethod());
        sysUser.setCreateTime(now);
        sysUser.setUpdateTime(now);
        //用户入库
        sysUserDao.insert(sysUser);
        SysUserDetail sysUserDetail = new SysUserDetail();
        sysUserDetail.setUserId(sysUser.getId());
        sysUserDetail.setNickName(registerRequest.getUsername());
        sysUserDetail.setCreateTime(now);
        sysUserDetail.setUpdateTime(now);
        //用户详细入库
        sysUserDetailDao.insert(sysUserDetail);
        logger.info("注册SysUser={}", JSON.toJSONString(sysUser));
        logger.info("注册SysUserDetail={}", JSON.toJSONString(sysUserDetail));
        logger.info("注册结束");
    }

}
