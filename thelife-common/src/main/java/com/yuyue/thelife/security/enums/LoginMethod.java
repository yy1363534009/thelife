package com.yuyue.thelife.security.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.yuyue.thelife.base.enums.abst.ConverTableEnum;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:51
 * @Description: 登录方式
 */
public enum LoginMethod implements ConverTableEnum<LoginMethod, Integer> {
    //
    WECHAT(0, "微信小程序"),
    CUSTOM(1, "自定义用户名"),
    MOBILE(2, "手机号"),
    EMAIL(3, "邮箱"),
    ;

    @EnumValue
    private Integer code;

    private String description;


    LoginMethod(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public LoginMethod getBycode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LoginMethod t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public LoginMethod returnEnum(Integer var1) {
        return getBycode(code);
    }
}
