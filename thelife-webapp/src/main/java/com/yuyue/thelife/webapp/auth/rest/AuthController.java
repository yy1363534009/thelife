package com.yuyue.thelife.webapp.auth.rest;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
import com.yuyue.thelife.base.security.dto.AuthUserDto;
import com.yuyue.thelife.base.wechat.request.WeChatAuthUserRequest;
import com.yuyue.thelife.webapp.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: yuyue
 * @create 2020/11/30 09:45
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        System.out.println("thelife-webapp/auth/login:" + authUserDto);
        return authService.login(authUserDto);
    }

    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        System.out.println(weChatAuthUserRequest);
        JsonRestResponseVo.Success(weChatAuthUserRequest);
        return JsonRestResponseVo.Success("post注册成功");
    }


}
