package com.yuyue.thelife.gateway.security.rest;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
import com.yuyue.thelife.base.security.dto.AuthUserDto;
import com.yuyue.thelife.base.wechat.request.WeChatAuthUserRequest;
import com.yuyue.thelife.gateway.security.dto.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 22:50:20
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        System.out.println("zuul/auth/login:" + authUserDto);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), authUserDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        return JsonRestResponseVo.Success(jwtUser);
    }

    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        System.out.println(weChatAuthUserRequest);
        JsonRestResponseVo.Success(weChatAuthUserRequest);
        return JsonRestResponseVo.Success("post注册成功");
    }

}