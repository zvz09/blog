package com.zvz.blog.controller;

import com.zvz.blog.model.Authors;
import com.zvz.blog.service.IUserService;
import com.zvz.blog.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping()
public class TestController {

    @Autowired
    IUserService userService;

    @RequestMapping("/test")
    public String imp(String fileName) throws Exception{
        String userName = "test";
        String userPass = "test123";
        String pass = MDUtil.generatePass(userName, userPass);
        int userStatus = 1;
        Authors authors = new Authors(userName,pass,userStatus);
        Integer result = userService.authorsAdd(authors);
        return result.toString();
    }
}
