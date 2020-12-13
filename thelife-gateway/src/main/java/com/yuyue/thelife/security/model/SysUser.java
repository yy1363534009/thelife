package com.yuyue.thelife.security.model;

import com.baomidou.mybatisplus.annotation.*;
import com.yuyue.thelife.security.enums.LoginMethod;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:32
 * @Description:
 */
@Data
@TableName("sys_user")
public class SysUser {

    /**
     * 主键 用户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录方式(0=移动端微信小程序，1=自定义用户名，2=手机号，3=邮箱，)
     */
    private LoginMethod loginMethod;

    /**
     * 帐户是否过期(1=未过期，0=已过期)
     */
    private Integer isAccountNonExpired = 1;

    /**
     * 帐户是否被锁定(1=未过期，0=已过期)
     */
    private Integer isAccountNonLocked = 1;

    /**
     * 密码是否过期(1=未过期，0=已过期)
     */
    private Integer isCredentialsNonExpired = 1;

    /**
     * 帐户是否可用(1=可用，0=删除用户)
     */
    private Integer isEnabled = 1;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
