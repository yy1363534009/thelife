package com.yuyue.thelife.base.enums;

import java.io.Serializable;

/**
 * @Auther: yuyue
 * @create 2020/12/3 15:19
 */
public interface ConverTableEnum<E extends Enum<?>, T> extends Serializable {

    T getCode();

    E returnEnum(T var1);

}
