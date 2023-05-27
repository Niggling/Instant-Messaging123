package com.example.demo.controller;

import com.example.demo.mapper.help.ExchangeMapper;
import com.example.demo.mapper.help.MyTeamMapper;
import com.example.demo.mapper.help.TeamMapper;
import com.example.demo.mapper.seven.TimeMapper;
import com.example.demo.mapper.seven.*;
import com.example.demo.pojo.help.*;
import com.example.demo.pojo.seven.Time;
import com.example.demo.pojo.seven.*;
import com.example.demo.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
public class HalfComplete {

}


