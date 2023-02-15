package com.shujuan.studyframework.common.config;

import com.shujuan.studyframework.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security配置类
 *
 * @Auther: guany
 * @Date: 2023/02/13
 */
@Configuration//@Configuration注解的类中，使用@Bean注解标注的方法，返回的类型都会直接注册为bean。
@EnableGlobalAuthentication //加载AuthenticationManager相关的配置类。被这个注解修饰的类，可以用来构建一个全局的AuthenticationManagerBuilder
@EnableWebSecurity //启动Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * http方式  请求的放行和限制
     * 该放行走 Spring Security 过滤器链，在过滤器链中给请求放行
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //用于配置直接放行的请求
                .antMatchers("/api/index/signUp","/api/index/login").permitAll()
                //其余请求都需要验证
                .anyRequest().authenticated()
                //授权码模式需要 会弹出默认自带的登录框
                .and().httpBasic()
                //禁用跨站伪造
                .and().csrf().disable();
        //如果项目没有前后端分离，还可以通过 formlogin配置登录相关的页面和请求处理
        // 使用自定义的认证过滤器
        // http.addFilterBefore(new  MyLoginFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * web方式  静态资源的放行
     * 该放行不走 Spring Security 过滤器链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 可以直接访问的静态数据
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/404.html")
                .antMatchers("/500.html")
                .antMatchers("/html/**")
                .antMatchers("/js/**");
    }

    /**
     * 设置授权处理相关的 具体类以及加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //隐藏未找到用户异常，默认为true
        provider.setHideUserNotFoundExceptions(true);
        // 用户认证service - 查询数据库的逻辑
        provider.setUserDetailsService(userDetailsService());
        //设置密码加密算法
        provider.setPasswordEncoder(passwordEncoder());

        auth.authenticationProvider(provider);
    }

    /**
     * 密码加密算法 BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 实现查询数据库用户数据，通过自定义的UserDetailsService
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     *  注入AuthenticationManager管理器
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
