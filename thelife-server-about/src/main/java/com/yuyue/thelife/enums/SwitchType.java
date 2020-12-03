package com.yuyue.thelife.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.yuyue.thelife.base.enums.ConverTableEnum;

/**
 * @author: yuyue
 * @create 2020/12/3 15:32
 */
public enum SwitchType implements ConverTableEnum<SwitchType, Integer> {

    OFF(0, "关闭"),
    ON(1, "开启");

    @EnumValue
    private Integer code;

    private String description;

    SwitchType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public SwitchType getBycode(Integer code) {
        if (code == null) {
            return null;
        }
        for (SwitchType t : values()) {
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
    public SwitchType returnEnum(Integer code) {
        return getBycode(code);
    }

}
