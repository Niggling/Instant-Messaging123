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
import com.example.demo.service.RegisterService;
import com.example.demo.utils.SqlSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    
    //注册
    @PostMapping("/add")
    @ResponseBody
    public String signup(@RequestBody User user)  throws SQLException, Exception{
        return registerService.register(user);
    }

}
