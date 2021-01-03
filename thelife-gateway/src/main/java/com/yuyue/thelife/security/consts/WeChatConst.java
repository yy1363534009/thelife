package com.yuyue.thelife.security.consts;

/**
 * @Author: yuyue
 * @Date: 2021/1/3 14:04
 * @Description: 微信小程序常量
 */
public class WeChatConst {

    /**
     * 获取openid的URL
     */
    public static final String OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 获取openid参数key→appid
     */
    public static final String OPENID_PARAM_KEY_APPID = "appid";

    /**
     * 获取openid参数key→js_code
     */
    public static final String OPENID_PARAM_KEY_JSCODE = "js_code";

    /**
     * 获取openid参数key→secret
     */
    public static final String OPENID_PARAM_KEY_SECRET = "secret";

    /**
     * 获取openid参数key→grant_type
     */
    public static final String OPENID_PARAM_KEY_GRANTTYPE = "grant_type";

    /**
     * 获取openid参数value→grant_type
     */
    public static final String OPENID_PARAM_VALUE_GRANTTYPE = "authorization_code";


}
