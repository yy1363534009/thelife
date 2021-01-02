package com.yuyue.thelife.exception.exception;

import com.yuyue.thelife.exception.enums.abst.AbstractBaseException;

/**
 * @Author: yuyue
 * @Date: 2021/1/2 10:53
 * @Description: 认证授权异常
 */
public class AuthException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String message;

    public AuthException(AbstractBaseException e) {
        super(e.getMessage());
        code = e.getCode();
        message = e.getMessage();
    }

}
