package com.shujuan.studyframework.api;

import com.shujuan.studyframework.common.result.Result;
import com.shujuan.studyframework.common.util.SecurityUtil;
import com.shujuan.studyframework.dto.UserDto;
import com.shujuan.studyframework.entity.UserEntity;
import com.shujuan.studyframework.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/index")
public class IndexApi {

    @Resource
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(IndexApi.class);

    @Resource
    AuthenticationManager authenticationManager;

    /**
     * 注册
     *
     * @param userEntity 用户注册信息
     * @return 注册结果
     * @throws Exception
     */
    @PostMapping(value = "/signUp")
    public Object signUp(@Valid @RequestBody UserEntity userEntity) throws Exception {
        userService.createUser(userEntity);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param user 用户名、密码、角色
     * @return 用户id
     */
    @PostMapping(value = "/login")
    public Object login(@Valid @RequestBody UserEntity user){
        UserDto userDto = SecurityUtil.login(user.getUsername(), user.getPassword(), authenticationManager);
        return Result.success(userDto);
    }


}
