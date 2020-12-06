package com.yuyue.thelife.base.about.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.yuyue.thelife.base.base.enums.ConverTableEnum;

/**
 * @Author: yuyue
 * @Date: 2020-12-03 21:53:30
 * @Description:
 */
public enum Type implements ConverTableEnum<Type, Integer> {

    //
    PC(0, "PC端"),
    WECHAT(1, "微信小程序");

    @EnumValue
    private Integer code;

    private String description;

    Type(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Type getBycode(Integer code) {
        if (code == null) {
            return null;
        }
        for (Type t : values()) {
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
    public Type returnEnum(Integer code) {
        return getBycode(code);
    }


}
