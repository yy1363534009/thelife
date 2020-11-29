package com.yuyue.thelife.zuul.security.service.impl;

import com.yuyue.thelife.zuul.security.service.dto.JwtUserDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JwtUserDto loadUserByUsername(String s) throws UsernameNotFoundException {
        JwtUserDto jwtUserDto = new JwtUserDto();
        jwtUserDto.setUsername("admin");
        jwtUserDto.setPassword(passwordEncoder.encode("1234"));
        return jwtUserDto;
    }

}