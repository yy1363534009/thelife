package com.yuyue.thelife.webapp.wechat.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * rest统一响应结果
 */
@Data
public class TheLifeResult implements Serializable {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public TheLifeResult() {
    }
    public TheLifeResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public TheLifeResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public TheLifeResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static TheLifeResult ok() {
        return new TheLifeResult(null);
    }
    public static TheLifeResult ok(String message) {
        return new TheLifeResult(message, null);
    }
    public static TheLifeResult ok(Object data) {
        return new TheLifeResult(data);
    }
    public static TheLifeResult ok(String message, Object data) {
        return new TheLifeResult(message, data);
    }

    public static TheLifeResult build(Integer code, String message) {
        return new TheLifeResult(code, message, null);
    }

    public static TheLifeResult build(Integer code, String message, Object data) {
        return new TheLifeResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 TheLifeResult 对象
     * @param json
     * @return
     */
    public static TheLifeResult format(String json) {
        try {
            return JSON.parseObject(json, TheLifeResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
