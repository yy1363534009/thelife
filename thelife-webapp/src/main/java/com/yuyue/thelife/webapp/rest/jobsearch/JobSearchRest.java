package com.yuyue.thelife.webapp.rest.jobsearch;

import com.yuyue.thelife.base.result.TheLifeResponse;
import com.yuyue.thelife.webapp.service.jobsearch.JobSearchService;
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
public class JobSearchRest {

    @Autowired
    private JobSearchService jobSearchService;

    @GetMapping
    public TheLifeResponse get() {
//        Object object = "获取找工作信息成功";
//        return TheLifeResult.ok(object);
        return jobSearchService.get();
    }

}
