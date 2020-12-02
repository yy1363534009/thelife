package com.yuyue.thelife.auth.service;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.dto.AuthUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: yuyue
 * @create 2020/11/30 09:48
 */
@FeignClient(name = "TheLife-Gateway")
public interface AuthService {

    @PostMapping(value = "/auth/login")
    JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto);

}
