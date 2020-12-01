package com.yuyue.thelife.gateway.security.config;

import com.yuyue.thelife.gateway.security.security.JwtAccessDeniedHandler;
import com.yuyue.thelife.gateway.security.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-26 22:19:29
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
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
        http
                // 禁用 CSRF
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()
                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 静态资源等等
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/webSocket/**"
                ).permitAll()
                // swagger 文档
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                // 文件
                .antMatchers("/avatar/**").permitAll()
                .antMatchers("/file/**").permitAll()
                // 阿里巴巴 druid
                .antMatchers("/druid/**").permitAll()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //微服务路径
                .antMatchers("/thelife-webapp/about", "/thelife-webapp/jobsearch").permitAll()
                //security登录接口
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/thelife-webapp/auth/login").permitAll()
                // 所有请求都需要认证
                .anyRequest().authenticated()
        ;
    }

}
