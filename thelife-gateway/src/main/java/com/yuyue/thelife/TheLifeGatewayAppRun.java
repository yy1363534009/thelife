package com.yuyue.thelife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: 网关启动器
 * @author: yuyue
 * @create: 2020-11-23 22:22:01
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class TheLifeGatewayAppRun {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeGatewayAppRun.class, args);
    }

}
