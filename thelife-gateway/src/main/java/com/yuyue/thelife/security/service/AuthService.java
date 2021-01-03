package com.yuyue.thelife.security.service;

import com.yuyue.thelife.security.param.AuthUserParam;
import com.yuyue.thelife.security.param.RegisterParam;
import com.yuyue.thelife.security.param.WeChatAuthUserParam;
import com.yuyue.thelife.security.result.AuthResult;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:25
 * @Description: 用户授权接口
 */
public interface AuthService {

    /**
     * pc段登录
     * @param authUserParam
     * @return
     */
    AuthResult login(AuthUserParam authUserParam);

    /**
     * 微信小程序登录
     * @param weChatAuthUserParam
     * @return
     */
    AuthResult wechatLogin(WeChatAuthUserParam weChatAuthUserParam);

    /**
     * 注册
     * @param registerParam
     */
    void register(RegisterParam registerParam);

}
