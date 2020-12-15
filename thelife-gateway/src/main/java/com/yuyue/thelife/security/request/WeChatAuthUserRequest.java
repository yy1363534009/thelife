package com.yuyue.thelife.security.request;

import com.yuyue.thelife.security.request.base.UserDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-24 23:21:30
 */
@Data
public class WeChatAuthUserRequest {

    /**
     * 用户登录凭证（有效期五分钟）。开发者需要在开发者服务器后台调用 auth.code2Session，使用 code 换取 openid 和 session_key 等信息
     * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html
     * 微信小程序用户登录流程：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     */
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 第一次登录，保存微信用户信息
     */
    private UserDetail userDetail;

}
