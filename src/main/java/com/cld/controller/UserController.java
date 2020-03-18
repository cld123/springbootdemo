package com.cld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/getUser")
    public String getUser(){
        return "hello world";
    }

    @RequestMapping("/test")
    public String interceptorTest(){
        logger.info("执行控制器------");
        return "interceptorTest";
    }
}
