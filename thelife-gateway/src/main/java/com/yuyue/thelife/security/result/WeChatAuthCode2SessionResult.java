package com.yuyue.thelife.security.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yuyue
 * @Date: 2021/1/3 14:44
 * @Description: 获取用户唯一标识OpenID和会话密钥session_key结果
 */
@Data
public class WeChatAuthCode2SessionResult implements Serializable {

    private static final long serialVersionUID = 8683980189554749808L;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明(https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html)
     */
    private String unionid;

    /**
     * 错误码:-1 系统繁忙，此时请开发者稍候再试,0 请求成功,40029 code无效,45011 频率限制，每个用户每分钟100次
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

}
