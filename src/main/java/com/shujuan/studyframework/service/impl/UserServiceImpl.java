package com.shujuan.studyframework.service.impl;

import com.shujuan.studyframework.common.exception.CustomException;
import com.shujuan.studyframework.common.util.SecurityUtil;
import com.shujuan.studyframework.entity.UserEntity;
import com.shujuan.studyframework.mapper.UserMapper;
import com.shujuan.studyframework.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int createUser(UserEntity user) throws Exception {
        //检验用户是否已存在
        Example example = new Example(UserEntity.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("username",user.getUsername());
        List<UserEntity> userEntities = userMapper.selectByExample(example);
        if(userEntities.size()!=0){
            throw new CustomException("该用户已存在");
        }

        UserEntity userEntity = user.getEntity(UserEntity.class);
        userEntity.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        return userMapper.insert(userEntity);
    }


}
