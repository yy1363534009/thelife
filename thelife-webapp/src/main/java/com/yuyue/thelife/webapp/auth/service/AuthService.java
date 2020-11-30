package com.yuyue.thelife.webapp.auth.service;

import com.yuyue.thelife.base.result.TheLifeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: yuyue
 * @create 2020/11/30 09:48
 */
@FeignClient(name = "TheLife-Zuul")
public interface AuthService {

    @GetMapping(value = "/auth/login")
    TheLifeResponse login1();

}
