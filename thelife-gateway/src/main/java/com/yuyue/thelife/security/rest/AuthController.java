package com.yuyue.thelife.security.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.config.bean.SecurityProperties;
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

    @Autowired
    private SecurityProperties properties;

    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), authUserDto.getPassword());
        // 未认证token令牌交给AuthenticationManager，通过自定义UserDetailsServiceImpl完成认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 将认证传递给Security上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户信息
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 构建jwt令牌
        String token = tokenProvider.createToken(authentication);
        // 封装返回信息
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", properties.getTokenStartWith() + token);
        result.put("user", jwtUser);
        return JsonRestResponseVo.Success(result);
    }

    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        return JsonRestResponseVo.Success();
    }

}