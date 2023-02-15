package com.shujuan.studyframework.mapper;

import com.shujuan.studyframework.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<UserEntity> {

    @Select("select * from t_user where username = #{username}")
    UserEntity selectByUserName(@Param("username") String username);
}
