package com.shujuan.studyframework.service;

import com.shujuan.studyframework.entity.UserEntity;

public interface UserService {

    int createUser(UserEntity userEntity) throws Exception;

}
