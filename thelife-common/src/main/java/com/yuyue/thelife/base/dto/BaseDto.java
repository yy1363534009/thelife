package com.yuyue.thelife.base.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yuyue
 * @create 2020/12/3 15:47
 */
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 1485120258917918503L;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
}
