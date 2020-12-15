package com.yuyue.thelife.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: rest统一响应结果
 * @author: yuyue
 * @create: 2020-11-23 23:42:16
 */
@Data
public class JsonRestResponseVo implements Serializable {

    private static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    private static final String DEFAULT_ERROR_MESSAGE = "请求异常";

    private static final Integer DEFAULT_SUCCESS_CODE = 200;

    private static final Integer DEFAULT_ERROR_CODE = 500;

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    public JsonRestResponseVo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonRestResponseVo(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static JsonRestResponseVo success() {
        return new JsonRestResponseVo(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE);
    }

    public static JsonRestResponseVo success(String message) {
        return new JsonRestResponseVo(DEFAULT_SUCCESS_CODE, message);
    }

    public static JsonRestResponseVo success(Object data) {
        return new JsonRestResponseVo(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static JsonRestResponseVo error(String message) {
        return new JsonRestResponseVo(DEFAULT_ERROR_CODE, message);
    }

    public static JsonRestResponseVo error(Object data) {
        return new JsonRestResponseVo(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE, data);
    }

    public static JsonRestResponseVo error(Integer status, String message) {
        return new JsonRestResponseVo(status, message);
    }

    public static JsonRestResponseVo error(Integer status, String message, Object data) {
        return new JsonRestResponseVo(status, message, data);
    }


    /**
     * JSON字符串转成 JsonRestResponseVo 对象
     *
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
