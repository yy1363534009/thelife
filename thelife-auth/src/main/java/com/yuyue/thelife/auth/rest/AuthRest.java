package com.yuyue.thelife.auth.rest;

import com.yuyue.thelife.base.result.TheLifeResponse;
import com.yuyue.thelife.base.wechat.request.WeChatAuthUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 22:50:20
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthRest {

    @PostMapping
    public TheLifeResponse login(WeChatAuthUserRequest weChatAuthUserRequest){
        System.out.println(weChatAuthUserRequest);
        return TheLifeResponse.ok("注册成功");
    }

}
