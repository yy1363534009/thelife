package com.yuyue.thelife.exception.exception;

import com.yuyue.thelife.exception.hanlder.AbstarctBaseException;

/**
 * @Author: yuyue
 * @Date: 2020/12/15 19:49
 * @Description: 业务异常
 */
public class ServiceException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer status;

    public ServiceException(AbstarctBaseException e) {
        super(e.getMessage());
        status = e.getCode();
    }

}
