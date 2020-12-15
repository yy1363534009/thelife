package com.yuyue.thelife.security.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.request.AuthUserRequest;
import com.yuyue.thelife.security.request.RegisterRequest;
import com.yuyue.thelife.security.request.WeChatAuthUserRequest;
import com.yuyue.thelife.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource(name = "authService")
    private AuthService authService;

    @ApiOperation(value = "登录授权", notes = "登录授权")
    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody @Validated AuthUserRequest authUserRequest) {
        return JsonRestResponseVo.success(authService.login(authUserRequest));
    }

    @ApiOperation(value = "微信小程序登录授权", notes = "微信小程序登录授权")
    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody @Validated WeChatAuthUserRequest weChatAuthUserRequest) {
        return JsonRestResponseVo.success(authService.wechatLogin(weChatAuthUserRequest));
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/register")
    public JsonRestResponseVo register(@RequestBody @Validated RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return JsonRestResponseVo.success();
    }

}