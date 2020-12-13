package com.yuyue.thelife.exception.hanlder;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 21:27
 * @Description:
 */
public class TheLifeException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer status;


    public TheLifeException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public TheLifeException(String message) {
        super(message);
    }


}
