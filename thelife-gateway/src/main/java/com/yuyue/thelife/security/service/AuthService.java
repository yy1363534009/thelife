package com.yuyue.thelife.security.service;

import com.yuyue.thelife.security.request.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:25
 * @Description:
 */
public interface AuthService extends UserDetailsService {

    void register(RegisterRequest registerRequest);

}
