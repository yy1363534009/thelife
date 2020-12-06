package com.yuyue.thelife.base.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.yuyue.thelife.base.base.enums.ConverTableEnum;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 21:10
 * @Description: 性别
 */
public enum Gender implements ConverTableEnum<Gender, Integer> {
    //
    unknown(0, "未知"),
    MEN(1, "男"),
    WOMEN(2, "女"),
    ;

    @EnumValue
    private Integer code;

    private String description;


    Gender(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Gender getBycode(Integer code) {
        if (code == null) {
            return null;
        }
        for (Gender t : values()) {
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
    public Gender returnEnum(Integer var1) {
        return getBycode(code);
    }
}
