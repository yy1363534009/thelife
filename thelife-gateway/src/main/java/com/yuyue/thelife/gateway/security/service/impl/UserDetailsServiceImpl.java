package com.yuyue.thelife.gateway.security.service.impl;

import com.yuyue.thelife.gateway.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-26 23:23:10
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = null;
        if ("admin".equals(username)) {
            jwtUser = new JwtUser("admin", passwordEncoder.encode("1234"), "阿凡达",
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"), true);
        } else {
            throw new RuntimeException("账号不存在");
        }
        return jwtUser;

    }

}