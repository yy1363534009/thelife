package com.yuyue.thelife.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @Description: 跨域资源共享配置
 * @author: yuyue
 * @create: 2020-11-25 23:20:01
 */
@Configuration
public class CorsConfig {

    @Resource
    private SecurityProperties properties;

    /**
     * 解决前后端分离中的cors跨域问题
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        //↓核心代码(请求头放行Authorization)
        corsConfiguration.addExposedHeader(properties.getHeader());
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
