package com.yuyue.thelife.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 22:39:58
 */
@SpringBootApplication
@EnableEurekaClient
public class TheLifeAuthApp {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeAuthApp.class, args);
    }

}
