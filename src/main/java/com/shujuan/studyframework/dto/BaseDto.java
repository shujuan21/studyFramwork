package com.shujuan.studyframework.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDto
 *
 * @author tz
 * @create 2021-05-10
 */
public class BaseDto implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private String nameCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 修改人
     */
    private String nameModified;

    /**
     * 是否删除 0：未删除 1：已删除
     */
    private Integer isDeleted = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getNameCreate() {
        return nameCreate;
    }

    public void setNameCreate(String nameCreate) {
        this.nameCreate = nameCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getNameModified() {
        return nameModified;
    }

    public void setNameModified(String nameModified) {
        this.nameModified = nameModified;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id='" + id + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", nameCreate='" + nameCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", nameModified='" + nameModified + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
