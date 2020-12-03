package com.yuyue.thelife.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-23 22:46:19
 */
@RestController
@RequestMapping(value = "/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @GetMapping
    public JsonRestResponseVo get() {
        Object object = "获取用户信息成功";
        return JsonRestResponseVo.Success(object);
    }

    @GetMapping(value = "/jobsearch")
    public JsonRestResponseVo getJobsearch(){
        System.out.println("/about/jobsearch");
        return aboutService.getJobsearch();
    }

}
