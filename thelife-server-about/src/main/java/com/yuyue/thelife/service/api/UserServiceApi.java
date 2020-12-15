package com.yuyue.thelife.service.api;

import com.yuyue.thelife.config.FeignConfig;
import com.yuyue.thelife.result.JsonRestResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: yuyue
 * @create 2020/11/30 11:34
 */
@FeignClient(name = "TheLife-Server-Jobsearch", configuration = FeignConfig.class)
public interface UserServiceApi {

    /**
     * 测试
     * @return
     */
    @GetMapping(value = "/jobsearch")
    JsonRestResponseVo getJobSearch();


}
