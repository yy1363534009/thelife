package com.yuyue.thelife.security.service;

import com.yuyue.thelife.security.request.AuthUserRequest;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.request.WeChatAuthUserRequest;

import java.util.Map;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:25
 * @Description: 用户授权接口
 */
public interface AuthService {

    /**
     * pc段登录
     * @param authUserRequest
     * @return
     */
    Map<String, Object> login(AuthUserRequest authUserRequest);

    /**
     * 微信小程序登录
     * @param weChatAuthUserRequest
     * @return
     */
    Map<String, Object> wechatLogin(WeChatAuthUserRequest weChatAuthUserRequest);

    /**
     * 注册
     * @param registerRequest
     */
    void register(RegisterRequest registerRequest);

}
