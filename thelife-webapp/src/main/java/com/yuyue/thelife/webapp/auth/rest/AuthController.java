package com.yuyue.thelife.webapp.auth.rest;

import com.yuyue.thelife.base.result.TheLifeResponse;
import com.yuyue.thelife.base.wechat.request.WeChatAuthUserRequest;
import com.yuyue.thelife.webapp.auth.service.auth.AuthService;
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
    public TheLifeResponse login(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest){
        System.out.println(weChatAuthUserRequest);
        TheLifeResponse.ok(weChatAuthUserRequest);
        return TheLifeResponse.ok("post注册成功");
    }

    @GetMapping(value = "/login")
    public TheLifeResponse login1(){
        System.out.println("thelife-webapp/auth/login");
        return authService.login1();
    }

}
