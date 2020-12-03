package com.yuyue.thelife.rest;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yuyue.thelife.enums.Type;
import com.yuyue.thelife.model.User;
import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.UserService;
import com.yuyue.thelife.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-23 22:46:19
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private UserService userService;

    @GetMapping
    public JsonRestResponseVo get() {
        Object object = "获取用户信息成功";
        return JsonRestResponseVo.Success(object);
    }

    @GetMapping(value = "/jobsearch")
    public JsonRestResponseVo getJobsearch() {
        System.out.println("/about/jobsearch");
        return userServiceApi.getJobsearch();
    }

    @PostMapping
    public JsonRestResponseVo insert() {
        long dateLong = new Date().getTime();
        User user = new User();
        user.setName("阿凡达" + dateLong);
        user.setType(Type.PC);
        Integer i = userService.insert(user);
        if (i == 1) {
            return JsonRestResponseVo.Success();
        }
        return JsonRestResponseVo.build(500, "FAILED");
    }

}
