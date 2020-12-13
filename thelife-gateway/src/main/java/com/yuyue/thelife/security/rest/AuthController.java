package com.yuyue.thelife.security.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.config.SecurityProperties;
import com.yuyue.thelife.security.dto.AuthUserDto;
import com.yuyue.thelife.security.dto.JwtUser;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.request.WeChatAuthUserRequest;
import com.yuyue.thelife.security.security.TokenProvider;
import com.yuyue.thelife.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-24 22:50:20
 */
@Api(tags = "系统：系统认证授权接口")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登录授权", notes = "登录授权")
    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        logger.info(authUserDto.toString());
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

    @ApiOperation(value = "微信小程序登录授权", notes = "微信小程序登录授权")
    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        logger.info(weChatAuthUserRequest.toString());
        // 获取用户唯一标识微信小程序openId，相当于用户名username
        String openId = "abcdefgh";
        // 去用户名表中查看openId是否注册，如果未注册就自动注册，因为微信小程序password不存，就指定。
        String password = "wechatpassword";
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(openId, password);
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

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/register")
    public JsonRestResponseVo register(@RequestBody @Validated RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return JsonRestResponseVo.Success();
    }

}