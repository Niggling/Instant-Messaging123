package com.example.demo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.seven.UserMapper;
import com.example.demo.pojo.seven.User;
import com.example.demo.utils.SqlSessionUtils;

import java.sql.SQLException;

@Service(value = "registerService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class)
public class RegisterService {
    
    public String register(User user)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //查找employeeID是否存在在员工
        UserMapper mapper0 = sqlSession.getMapper(UserMapper.class);
        User he = mapper0.selectEmployeeByEid(user.getEmployeeID());
        if (he == null) {
            return "你的员工号输入错误";
        }
        System.out.println(he);
        //查找employeeID是否不在用户表内
        //有账户了不给注册
        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
        User hello = mapper1.selectUserByEid(user.getEmployeeID());
        if (hello == null) {
            //前端已经做过确认密码了
            user.setPassword(user.getPassword());
            UserMapper mapper2 = sqlSession.getMapper(UserMapper.class);
            mapper2.insertUser(user.getEmployeeID(), user.getUsername(), user.getPassword(),"1","0");
            return "已注册";
        } else return "该员工号下已有账号，无需注册";
    }
}
