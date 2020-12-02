package com.yuyue.thelife.security.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.dto.AuthUserDto;
import com.yuyue.thelife.security.dto.JwtUser;
import com.yuyue.thelife.security.security.TokenProvider;
import com.yuyue.thelife.wechat.request.WeChatAuthUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        System.out.println("zuul/auth/login:" + authUserDto);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), authUserDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", token);
        result.put("user", jwtUser);
        return JsonRestResponseVo.Success(result);
    }

    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        System.out.println(weChatAuthUserRequest);
        JsonRestResponseVo.Success(weChatAuthUserRequest);
        return JsonRestResponseVo.Success("post注册成功");
    }

}