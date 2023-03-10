package com.shujuan.studyframework.service.impl;

import com.shujuan.studyframework.common.exception.CustomException;
import com.shujuan.studyframework.dto.UserDto;
import com.shujuan.studyframework.entity.UserEntity;
import com.shujuan.studyframework.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: guany
 * @Date: 2023/02/13
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    /**
     * 这里根据传进来的用户账号进行用户信息的构建
     * 通常的做法是
     *  1 根据username查询数据库对应的用户信息
     *  2 根据用户信息查询出用户权限信息  例如菜单添加权限  sys:menu:add
     *  3 根据用户账号，密码，权限构建对应的UserDetails对象返回
     * 这里实际上是没有进行用户认证功能的，真正的验证是在UsernamePasswordAuthenticationFilter对象当中
     * UsernamePasswordAuthenticationFilter对象会自动根据前端传入的账号信息和UserDetails对象对比进行账号的验证
     * 通常情况下，已经满足常见的使用常见，不过如果有特殊需求，需要使用自己实现的具体认证方式，可以继承UsernamePasswordAuthenticationFilter对象
     * 重写attemptAuthentication 方法和successfulAuthentication方法
     * 最后在WebSecurityConfiguration里面添加自己的过滤器即可
     * @param username 用户账号
     * @return UserInfo
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 当前使用测试数据 ,不限制用户账号。密码设置123456就通过验证，并添加权限
        UserEntity user = userMapper.selectByUserName(username);
        if(user == null){
            throw new CustomException("用户名或密码错误");
        }
        String password = user.getPassword();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("sys:menu:add");
        List<GrantedAuthority> authorities =new ArrayList<>();
        authorities.add(authority);
        UserDto userDto =new UserDto(username,password,authorities);
        userDto.setUserId("11111111");
        return userDto;
    }
}
