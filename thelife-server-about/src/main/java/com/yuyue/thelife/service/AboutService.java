package com.yuyue.thelife.service;

import com.yuyue.thelife.config.FeignConfig;
import com.yuyue.thelife.result.JsonRestResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: yuyue
 * @create 2020/11/30 11:34
 */
//@FeignClient(name = "TheLife-Server-Jobsearch", configuration = FeignConfig.class, fallback = AboutServiceFallBack.class)
@FeignClient(name = "TheLife-Server-Jobsearch", configuration = FeignConfig.class)
public interface AboutService {

    @GetMapping(value = "/jobsearch")
    JsonRestResponseVo getJobsearch();

}
