package com.yuyue.thelife.zuul.security.rest;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
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
    public JsonRestResponseVo login(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest){
        System.out.println(weChatAuthUserRequest);
        JsonRestResponseVo.Success(weChatAuthUserRequest);
        return JsonRestResponseVo.Success("post注册成功");
    }

    @GetMapping(value = "/login")
    public JsonRestResponseVo login1(){
        System.out.println("/auth/login");
        return JsonRestResponseVo.Success("get注册成功");
    }

}