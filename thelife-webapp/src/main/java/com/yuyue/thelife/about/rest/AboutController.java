package com.yuyue.thelife.about.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
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
    public JsonRestResponseVo get() {
        Object object = "获取用户信息成功";
        return JsonRestResponseVo.Success(object);
    }

}
