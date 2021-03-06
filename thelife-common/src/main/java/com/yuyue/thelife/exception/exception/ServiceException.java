package com.yuyue.thelife.exception.exception;

import com.yuyue.thelife.exception.enums.abst.AbstractBaseException;

/**
 * @Author: yuyue
 * @Date: 2020/12/15 19:49
 * @Description: 业务异常
 */
public class ServiceException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String message;

    public ServiceException(AbstractBaseException e) {
        super(e.getMessage());
        code = e.getCode();
        message = e.getMessage();
    }

}
