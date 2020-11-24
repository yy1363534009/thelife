package com.yuyue.thelife.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * rest统一响应结果
 */
@Data
public class TheLifeResponse implements Serializable {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public TheLifeResponse() {
    }
    public TheLifeResponse(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public TheLifeResponse(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public TheLifeResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static TheLifeResponse ok() {
        return new TheLifeResponse(null);
    }
    public static TheLifeResponse ok(String message) {
        return new TheLifeResponse(message, null);
    }
    public static TheLifeResponse ok(Object data) {
        return new TheLifeResponse(data);
    }
    public static TheLifeResponse ok(String message, Object data) {
        return new TheLifeResponse(message, data);
    }

    public static TheLifeResponse build(Integer code, String message) {
        return new TheLifeResponse(code, message, null);
    }

    public static TheLifeResponse build(Integer code, String message, Object data) {
        return new TheLifeResponse(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 TheLifeResponse 对象
     * @param json
     * @return
     */
    public static TheLifeResponse format(String json) {
        try {
            return JSON.parseObject(json, TheLifeResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}