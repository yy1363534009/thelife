package com.yuyue.thelife.security.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.param.AuthUserParam;
import com.yuyue.thelife.security.param.RegisterParam;
import com.yuyue.thelife.security.param.WeChatAuthUserParam;
import com.yuyue.thelife.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Resource(name = "authService")
    private AuthService authService;

    @ApiOperation(value = "pc登录授权", notes = "pc登录授权")
    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody @Validated AuthUserParam authUserParam) {
        return JsonRestResponseVo.success(authService.login(authUserParam));
    }

    @ApiOperation(value = "微信小程序登录授权", notes = "微信小程序登录授权")
    @PostMapping(value = "/wechatLogin")
    public JsonRestResponseVo wechatLogin(@RequestBody @Validated WeChatAuthUserParam weChatAuthUserParam) {
        return JsonRestResponseVo.success(authService.wechatLogin(weChatAuthUserParam));
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/register")
    public JsonRestResponseVo register(@RequestBody @Validated RegisterParam registerParam) {
        authService.register(registerParam);
        return JsonRestResponseVo.success();
    }

}