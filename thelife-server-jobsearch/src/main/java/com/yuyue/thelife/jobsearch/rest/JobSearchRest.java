package com.yuyue.thelife.jobsearch.rest;

import com.yuyue.thelife.base.result.TheLifeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-23 23:42:16
 */
@RestController
@RequestMapping(value = "/jobsearch")
public class JobSearchRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping
    public TheLifeResponse get() {
        Object object = "获取找工作信息成功" + applicationName;
        return TheLifeResponse.ok(object);
    }

}
