package com.yuyue.thelife.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * rest统一响应结果
 */
@Data
public class JsonRestResponseVo implements Serializable {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public JsonRestResponseVo() {
    }
    public JsonRestResponseVo(Object data) {
        this.status = 200;
        this.message = "SUCCESS";
        this.data = data;
    }
    public JsonRestResponseVo(String message, Object data) {
        this.status = 200;
        this.message = message;
        this.data = data;
    }

    public JsonRestResponseVo(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static JsonRestResponseVo Success() {
        return new JsonRestResponseVo(null);
    }
    public static JsonRestResponseVo Success(String message) {
        return new JsonRestResponseVo(message, null);
    }
    public static JsonRestResponseVo Success(Object data) {
        return new JsonRestResponseVo(data);
    }
    public static JsonRestResponseVo Success(String message, Object data) {
        return new JsonRestResponseVo(message, data);
    }

    public static JsonRestResponseVo build(Integer status, String message) {
        return new JsonRestResponseVo(status, message, null);
    }

    public static JsonRestResponseVo build(Integer status, String message, Object data) {
        return new JsonRestResponseVo(status, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 JsonRestResponseVo 对象
     * @param json
     * @return
     */
    public static JsonRestResponseVo format(String json) {
        try {
            return JSON.parseObject(json, JsonRestResponseVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
