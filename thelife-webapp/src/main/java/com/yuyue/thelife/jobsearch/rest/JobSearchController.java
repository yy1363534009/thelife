package com.yuyue.thelife.jobsearch.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.jobsearch.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-23 23:24:24
 */
@RestController
@RequestMapping(value = "/jobsearch")
public class JobSearchController {

    @Autowired
    private JobSearchService jobSearchService;

    @GetMapping
    public JsonRestResponseVo get() {
//        Object object = "获取找工作信息成功";
//        return TheLifeResult.ok(object);
        return jobSearchService.get();
    }

}
