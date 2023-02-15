package com.shujuan.studyframework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_book")
public class BookEntity {

    @Id
    @Column(name = "id")
//    @GeneratedValue(
//            strategy = GenerationType.IDENTITY,
//            generator = "select replace(uuid(), '-', '')" //uuid() 是 mysql自带函数
//    )
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "bookType")
    private String bookType;

    @Column(name = "book_num")
    private String bookNum;

    @Column(name = "publish_time")
    private Date publishTime;

    public BookEntity(String bookName, String bookType, String bookNum, Date publishTime) {
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookNum = bookNum;
        this.publishTime = publishTime;
    }

    public BookEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
