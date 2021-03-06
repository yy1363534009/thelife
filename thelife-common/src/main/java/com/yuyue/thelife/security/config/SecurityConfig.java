package com.yuyue.thelife.security.config;

import com.yuyue.thelife.security.security.JwtAccessDeniedHandler;
import com.yuyue.thelife.security.security.JwtAuthenticationEntryPoint;
import com.yuyue.thelife.security.security.TokenConfigurer;
import com.yuyue.thelife.security.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-26 22:19:29
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 跨域
     */
    @Resource
    private CorsFilter corsFilter;

    /**
     * 未认证处理
     */
    @Resource(name = "jwtAuthenticationEntryPoint")
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    /**
     * 未授权处理
     */
    @Resource(name = "jwtAccessDeniedHandler")
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * token工具
     */
    @Resource(name = "tokenProvider")
    private TokenProvider tokenProvider;

    /**
     * 密码加密方式
     *
     * @return
     */
    @Bean("bCryptPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
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
                //security登录接口
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/wechatLogin").permitAll()
                .antMatchers("/auth/register").permitAll()
                // 所有请求都需要认证
                .anyRequest().authenticated()
                .and().apply(new TokenConfigurer(tokenProvider))
        ;
    }

}
