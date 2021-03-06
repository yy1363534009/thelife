package com.yuyue.thelife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 【找活】服务启动器
 * @author: yuyue
 * @create: 2020-11-23 23:39:29
 */
@SpringBootApplication
@EnableEurekaClient
public class TheLifeJobsearchAppRun {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeJobsearchAppRun.class, args);
    }

}
