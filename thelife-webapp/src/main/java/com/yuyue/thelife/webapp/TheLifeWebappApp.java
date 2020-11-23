package com.yuyue.thelife.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: rest接口启动器
 * @Auther: yuyue
 * @create: 2020-11-23 22:36:44
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.yuyue.thelife.webapp")
public class TheLifeWebappApp {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeWebappApp.class, args);
    }

}
