package com.shujuan.studyframework.entity;


import com.shujuan.studyframework.common.UUIdGenId;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="t_user")
public class UserEntity {

    @Id
    @Column(name = "id")
    @KeySql(genId = UUIdGenId.class) //自定义主键生成策略
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 密码
     */
    @Length(min = 6,message = "密码至少6位")
    @NotBlank(message = "密码不能为空")
    @Column(name = "password")
    private String password;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 电话号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 生日 YYYY-MM-dd
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 角色
     */
    @Column(name = "role")
    private String role;

    public <T> T getEntity(Class<T> clazz) throws Exception{
        T entity = clazz.newInstance();
        BeanUtils.copyProperties(this, entity); //浅拷贝
        return entity;
    }

    public UserEntity(String username, String realName, String password, String sex, String phoneNumber, String address, String birthday, String role) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.role = role;
    }

    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}