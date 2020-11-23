package com.yuyue.thelife.webapp.wechat.service;

import com.yuyue.thelife.webapp.wechat.base.result.TheLifeResult;
import com.yuyue.thelife.webapp.wechat.service.fallback.JobSearchServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 00:02:42
 */
@FeignClient(value = "TheLife-Server-Jobsearch", fallback = JobSearchServiceFallBack.class)
public interface JobSearchService {

    @GetMapping(value = "/jobsearch/wechat")
    public TheLifeResult get();

}
