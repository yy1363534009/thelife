package com.yuyue.thelife.webapp.rest.about;

import com.yuyue.thelife.base.result.TheLifeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-23 22:46:19
 */
@RestController
@RequestMapping(value = "/about")
public class AboutRest {

    @GetMapping
    public TheLifeResponse get() {
        Object object = "获取用户信息成功";
        return TheLifeResponse.ok(object);
    }

}
