package com.yuyue.thelife.base.about.moduls;

import com.baomidou.mybatisplus.annotation.*;
import com.yuyue.thelife.base.about.enums.Type;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yuyue
 * @create 2020/12/3 15:24
 */
@Data
@TableName("t_user")
public class User implements Serializable{

    private static final long serialVersionUID = 8508852838896210410L;

    /**
     * 主键
     */
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private String name;

    private Type type;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
