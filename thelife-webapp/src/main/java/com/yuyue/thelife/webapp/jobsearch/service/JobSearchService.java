package com.yuyue.thelife.webapp.jobsearch.service;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
import com.yuyue.thelife.webapp.jobsearch.service.fallback.JobSearchServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 00:02:42
 */
@FeignClient(value = "TheLife-Server-Jobsearch", fallback = JobSearchServiceFallBack.class)
public interface JobSearchService {

    @GetMapping(value = "/jobsearch")
    JsonRestResponseVo get();

}
