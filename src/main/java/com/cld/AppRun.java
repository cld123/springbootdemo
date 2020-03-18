package com.cld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动类规则
 * 1.启动类位置：主包下面和子包同级
 * 2.需要一个@SpringBootApplication
 */
@MapperScan("com.cld.dao")
@SpringBootApplication
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }
}
