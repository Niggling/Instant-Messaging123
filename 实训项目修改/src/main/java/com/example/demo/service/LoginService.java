package com.example.demo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.seven.UserMapper;
import com.example.demo.pojo.seven.User;
import com.example.demo.utils.SqlSessionUtils;

import java.sql.SQLException;

@Service(value = "loginService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class)
public class LoginService {
    
    public boolean login(User user) throws SQLException, Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //查找employeeID是否存在在员工
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User he = mapper.login(user.getEmployeeID(), user.getPassword());
        if(he == null){
            return false;
        }
        else return true;
        // 默认是开启记住我的
    }

}
