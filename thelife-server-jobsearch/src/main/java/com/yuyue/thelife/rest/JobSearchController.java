package com.yuyue.thelife.rest;

import com.yuyue.thelife.result.JsonRestResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-23 23:42:16
 */
@Api(tags = "服务：找活接口")
@RestController
@RequestMapping(value = "/jobsearch")
public class JobSearchController {

    @Value("${spring.application.name}")
    private String applicationName;

    @ApiOperation(value = "获取找活列表",notes = "获取找活列表")
    @GetMapping
    public JsonRestResponseVo get() {
        Object object = "获取找工作信息成功" + applicationName;
        return JsonRestResponseVo.success(object);
    }

}

