package com.yuyue.thelife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: 注册中心启动器
 * @Auther: yuyue
 * @create: 2020-11-23 22:01:47
 */
@SpringBootApplication
@EnableEurekaServer
public class TheLifeEurekaAppRun {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeEurekaAppRun.class, args);
    }

}
