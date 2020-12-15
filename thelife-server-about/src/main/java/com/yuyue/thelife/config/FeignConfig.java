package com.yuyue.thelife.config;

import com.yuyue.thelife.security.config.SecurityProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: yuyue
 * @create 2020/12/3 10:13
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    @Resource
    private SecurityProperties properties;

    /**
     * 获取当前请求头中的token，调用其他服务接口将token放到请求中，完成其他服务认证授权
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("FeignConfig-----hearder:" + properties.getHeader());
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        requestTemplate.header(properties.getHeader(), request.getHeader(properties.getHeader()));
    }

}
