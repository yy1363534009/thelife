package com.yuyue.thelife.auth.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-26 22:19:29
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 当前将所有请求放行,交给资源配置类进行资源权限判断
     * 因为默认情况下会拦截所有请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/thelife-webapp/about","/thelife-webapp/jobsearch","").permitAll().
        anyRequest().authenticated();// 所有进入应用的HTTP请求都要进行认证，授权
    }

}
