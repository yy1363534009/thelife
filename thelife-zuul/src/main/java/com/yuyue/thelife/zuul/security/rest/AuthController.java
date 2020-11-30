package com.yuyue.thelife.zuul.security.rest;

import com.yuyue.thelife.base.result.TheLifeResponse;
import com.yuyue.thelife.base.wechat.request.WeChatAuthUserRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 22:50:20
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @PostMapping(value = "/login")
    public TheLifeResponse login(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest){
        System.out.println(weChatAuthUserRequest);
        TheLifeResponse.Success(weChatAuthUserRequest);
        return TheLifeResponse.Success("post注册成功");
    }

    @GetMapping(value = "/login")
    public TheLifeResponse login1(){
        System.out.println("/auth/login");
        return TheLifeResponse.Success("get注册成功");
    }

}