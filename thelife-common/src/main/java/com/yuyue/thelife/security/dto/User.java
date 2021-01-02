package com.yuyue.thelife.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuyue.thelife.security.enums.LoginMethod;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yuyue
 * @Date: 2021/1/2 11:44
 * @Description: 用户
 */
@Getter
@Setter
public class User implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 登录方式(0=移动端微信小程序，1=自定义用户名，2=手机号，3=邮箱，)
     */
    @JsonIgnore
    private LoginMethod loginMethod;

    /**
     * 帐户是否过期(1=未过期，0=已过期)
     */
    @JsonIgnore
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被锁定(1=未过期，0=已过期)
     */
    @JsonIgnore
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期(1=未过期，0=已过期)
     */
    @JsonIgnore
    private boolean isCredentialsNonExpired = true;

    /**
     * 帐户是否可用(1=可用，0=删除用户)
     */
    @JsonIgnore
    private boolean isEnabled = true;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return isEnabled;
    }

}
