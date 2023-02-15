package com.shujuan.studyframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.shujuan.studyframework.mapper")
public class StudyFrameworkApplication {

//    LoggerFactory.g


    public static void main(String[] args) {
        SpringApplication.run(StudyFrameworkApplication.class, args);
    }

}
