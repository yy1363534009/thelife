package com.yuyue.thelife.service;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.fallback.AboutServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: yuyue
 * @create 2020/11/30 11:34
 */
@FeignClient(name = "TheLife-Server-Jobsearch",fallback = AboutServiceFallBack.class)
public interface AboutService {

    @GetMapping(value = "/jobsearch")
    JsonRestResponseVo getJobsearch();

}
