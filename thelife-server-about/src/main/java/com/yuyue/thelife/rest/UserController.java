package com.yuyue.thelife.rest;

import com.yuyue.thelife.enums.Type;
import com.yuyue.thelife.model.User;
import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.UserService;
import com.yuyue.thelife.service.api.UserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-23 22:46:19
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserServiceApi userServiceApi;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping
    public JsonRestResponseVo get() {
        Object object = "获取用户信息成功";
        return JsonRestResponseVo.success(object);
    }

    @GetMapping(value = "/jobsearch")
    public JsonRestResponseVo getJobsearch() {
        return userServiceApi.getJobSearch();
    }

    @PostMapping
    public JsonRestResponseVo insert() {
        long dateLong = System.currentTimeMillis();
        User user = new User();
        user.setName("阿凡达" + dateLong);
        user.setType(Type.PC);
        boolean b = userService.insert(user);
        if (b) {
            return JsonRestResponseVo.success();
        }
        return JsonRestResponseVo.error("FAILED");
    }

}
