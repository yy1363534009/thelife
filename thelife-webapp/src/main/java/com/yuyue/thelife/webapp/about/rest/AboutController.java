package com.yuyue.thelife.webapp.about.rest;

import com.yuyue.thelife.base.result.TheLifeResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-23 22:46:19
 */
@RestController
@RequestMapping(value = "/about")
public class AboutController {

    @GetMapping
    public TheLifeResponse get() {
        Object object = "获取用户信息成功";
        return TheLifeResponse.Success(object);
    }

}
