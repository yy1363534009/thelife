package com.yuyue.thelife.auth.rest;

import com.yuyue.thelife.webapp.wechat.base.result.TheLifeResult;
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
    public TheLifeResult login(){
        return TheLifeResult.ok("注册成功");
    }

}
