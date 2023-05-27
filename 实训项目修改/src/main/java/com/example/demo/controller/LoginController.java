package com.example.demo.controller;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.mapper.seven.UserMapper;
import com.example.demo.pojo.seven.User;
import com.example.demo.service.LoginService;
import com.example.demo.utils.SqlSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping("/check")
    @ResponseBody
    public boolean login(@RequestBody User user)  throws SQLException, Exception{
        return loginService.login(user);
    }

}
