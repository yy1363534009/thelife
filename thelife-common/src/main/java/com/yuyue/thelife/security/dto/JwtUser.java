package com.yuyue.thelife.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-29 15:10:27
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

    /**
     * 用户信息
     */
    private User user;

    /**
     * 用户详情信息
     */
    private UserDetail userDetail;

    /**
     * 权限
     */
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;


    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 用户账号是痘过期
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    /**
     * 用户账号是否被锁定
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    /**
     * 用户密码是否过期
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    /**
     * 用户是否被禁用：true=>不禁用，false=>禁用
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }


}
