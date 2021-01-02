package com.yuyue.thelife.exception.enums;

import com.yuyue.thelife.exception.enums.abst.AbstractBaseException;

/**
 * @Author: yuyue
 * @Date: 2020/12/15 19:58
 * @Description: 认证授权异常枚举
 */
public enum AuthExceptionEnum implements AbstractBaseException {
    /**
     * 用户名或密码不正确
     */
    USERNAMEPASSWORD_NOTFOUND(1, "用户名或密码不正确，请检查！"),
    /**
     * 账号未激活
     */
    USER_ISENABLED(2, "账号未激活，请检查！"),
    /**
     * 用户已存在
     */
    USER_EXIST(3, "用户已存在，请检查！")
    ;

    private Integer code;

    private String message;

    AuthExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
