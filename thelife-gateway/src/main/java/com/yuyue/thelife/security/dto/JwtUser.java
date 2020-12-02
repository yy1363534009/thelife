package com.yuyue.thelife.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-29 15:10:27
 */
@Getter //提供get方法
@AllArgsConstructor //全部参数构造器
public class JwtUser implements UserDetails {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore//返回json数据时不被包含
    private String password;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 用户是否被禁用：true=>不禁用，false=>禁用
     */
    private boolean isEnabled;

    /**
     * 用户账号是痘过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
