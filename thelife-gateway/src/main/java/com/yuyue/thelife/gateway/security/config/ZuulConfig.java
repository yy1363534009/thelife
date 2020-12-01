package com.yuyue.thelife.gateway.security.config;

import com.yuyue.thelife.gateway.security.config.bean.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description: 网关服务配置
 * @Auther: yuyue
 * @create: 2020-11-25 23:20:01
 */
@Configuration
public class ZuulConfig {

    @Autowired
    private SecurityProperties properties;

    /**
     * 解决前后端分离中的cors跨域问题
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
        corsConfiguration.addExposedHeader(properties.getHeader());//
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
