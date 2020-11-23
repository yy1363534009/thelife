package com.yuyue.thelife.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: 网关启动器
 * @Auther: yuyue
 * @create: 2020-11-23 22:22:01
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class TheLifeZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeZuulApp.class, args);
    }

}
