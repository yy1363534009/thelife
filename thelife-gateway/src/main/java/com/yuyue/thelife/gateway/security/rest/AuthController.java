package com.yuyue.thelife.gateway.security.rest;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
import com.yuyue.thelife.base.security.dto.AuthUserDto;
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
    public JsonRestResponseVo login(@RequestBody AuthUserDto authUserDto) {
        System.out.println("zuul/auth/login:" + authUserDto);
        return JsonRestResponseVo.Success("zuul/auth/login:" + authUserDto.toString());
    }

    @PostMapping(value = "/wechat/login")
    public JsonRestResponseVo wechatLogin(@RequestBody WeChatAuthUserRequest weChatAuthUserRequest) {
        System.out.println(weChatAuthUserRequest);
        JsonRestResponseVo.Success(weChatAuthUserRequest);
        return JsonRestResponseVo.Success("post注册成功");
    }

}