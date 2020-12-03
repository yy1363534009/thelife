package com.yuyue.thelife.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yuyue.thelife.security.config.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-25 23:03:36
 */
@Component
public class SecurityFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private SecurityProperties properties;


    /**
     * 返回值可取：
     * - pre：在请求路由之前执行
     * - route：在请求路由时调用
     * - post：请求路由之后调用， 也就是在route和errror过滤器之后调用
     * - error：处理请求发生错误时调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回值是int，会根据返回值进行定义过滤器的执行顺序，值越小优先级越大
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否被执行，true则执行，false不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义过滤功能
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //1.zuul提供获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();
        System.out.println("ZuulFilter拦截请求：[" + request.getMethod() + "]" + request.getRequestURL());
        String token = resolveToken(request);
        System.out.println("ZuulFilter请求头token：" + token);
        // 对于 Token 为空的不需要去查 Redis
        if (StrUtil.isNotBlank(token)) {

        }
        return null;
    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }

}
