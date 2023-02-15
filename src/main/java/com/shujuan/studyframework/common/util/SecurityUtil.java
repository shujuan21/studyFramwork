package com.shujuan.studyframework.common.util;

import com.shujuan.studyframework.common.exception.CustomException;
import com.shujuan.studyframework.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityUtil 工具类
 *
 * @Auther: guany
 * @Date: 2023/02/13
 */
public class SecurityUtil {

    /**
     * 根据账号密码进行调用security进行认证授权
     * 主动调用AuthenticationManager的authenticate方法实现
     * 授权成功后将用户信息存入SecurityContext当中
     * @param username 用户名
     * @param password 密码
     * @param authenticationManager 认证授权管理器,
     * @see  AuthenticationManager
     * @return UserDto  用户信息
     */
    public static UserDto login(String username, String password, AuthenticationManager authenticationManager){
        //将用户名和密码封装在token中。（使用security框架自带的验证token生成器  也可以自定义）
        UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(username,password );
        // 执行登录认证过程 （内部调用UserDetailsServiceImpl的loadUserByUsername方法）
        Authentication authenticate = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文。（SecurityContextHolder是工具类，保存当前使用人的安全上下文）
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 生成令牌并返回给客户端
        UserDto userDto = (UserDto) authenticate.getPrincipal();
        return userDto;
    }

    /**
     * 获取当前登录的所有认证信息
     */
    public static Authentication getAuthentication(){
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    /**
     * 获取当前登录用户信息
     */
    public static UserDto getUserInfo(){
        Authentication authentication = getAuthentication();
        if(authentication!=null){
            Object principal = authentication.getPrincipal();//获取当前用户的信息
            if(principal!=null){
                return (UserDto) principal;
            }
        }
        throw new CustomException();
    }

    /**
     * 获取当前登录用户ID
     */
    public static String getUserId(){
        UserDto userDto = getUserInfo();
        return userDto.getUserId();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
