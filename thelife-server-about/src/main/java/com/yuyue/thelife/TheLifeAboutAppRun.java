package com.yuyue.thelife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @Description: rest接口启动器
 * @Auther: yuyue
 * @create: 2020-11-23 22:36:44
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.yuyue.thelife")//开通Feign http接口调用服务
public class TheLifeAboutAppRun {

    public static void main(String[] args) {
        SpringApplication.run(TheLifeAboutAppRun.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}
