package com.yuyue.thelife.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.yuyue.thelife.exception.enums.AuthExceptionEnum;
import com.yuyue.thelife.exception.exception.ServiceException;
import com.yuyue.thelife.security.config.SecurityProperties;
import com.yuyue.thelife.security.dao.SysUserDao;
import com.yuyue.thelife.security.dao.SysUserDetailDao;
import com.yuyue.thelife.security.dto.JwtUser;
import com.yuyue.thelife.security.enums.LoginMethod;
import com.yuyue.thelife.security.model.SysUser;
import com.yuyue.thelife.security.model.SysUserDetail;
import com.yuyue.thelife.security.request.AuthUserRequest;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.request.WeChatAuthUserRequest;
import com.yuyue.thelife.security.security.TokenProvider;
import com.yuyue.thelife.security.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 17:13
 * @Description: 用户授权接口实现类
 */
@Service("authService")
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource(name = "tokenProvider")
    private TokenProvider tokenProvider;

    @Resource
    private SecurityProperties properties;

    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Resource(name = "sysUserDetailDao")
    private SysUserDetailDao sysUserDetailDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> login(AuthUserRequest authUserRequest) {
        logger.info(authUserRequest.toString());
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword());
        // 未认证token令牌交给AuthenticationManager，通过自定义UserDetailsServiceImpl完成认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 将认证传递给Security上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户信息
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 构建jwt令牌
        String token = tokenProvider.createToken(authentication);
        // 封装返回信息
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", properties.getTokenStartWith() + token);
        result.put("user", jwtUser);
        return result;
    }

    @Override
    public Map<String, Object> wechatLogin(WeChatAuthUserRequest weChatAuthUserRequest) {
        logger.info(weChatAuthUserRequest.toString());
        // 获取用户唯一标识微信小程序openId，相当于用户名username
        String openId = "abcdefgh";
        // 去用户名表中查看openId是否注册，如果未注册就自动注册，因为微信小程序password不存，就指定。
        String password = "wechatpassword";
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(openId, password);
        // 未认证token令牌交给AuthenticationManager，通过自定义UserDetailsServiceImpl完成认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 将认证传递给Security上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户信息
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 构建jwt令牌
        String token = tokenProvider.createToken(authentication);
        // 封装返回信息
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", properties.getTokenStartWith() + token);
        result.put("user", jwtUser);
        return result;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        logger.info("注册开始-当前用户：RegisterRequest={}", JSON.toJSONString(registerRequest));
        SysUser user = sysUserDao.queryByUsername(registerRequest.getUsername());
        if (user != null) {
            throw new ServiceException(AuthExceptionEnum.USER_EXIST);
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

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = sysUserDao.queryByUsername(username);
        if (user == null) {
            throw new ServiceException(AuthExceptionEnum.USERNAMEPASSWORD_NOTFOUND);
        }
        if (user.getIsEnabled() != 1) {
            throw new ServiceException(AuthExceptionEnum.USER_ISENABLED);
        }
        JwtUser jwtUser = new JwtUser(user.getUsername(), user.getPassword(), user.getUsername(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"), user.getIsEnabled() == 1 ? true : false);
        return jwtUser;

    }

}
