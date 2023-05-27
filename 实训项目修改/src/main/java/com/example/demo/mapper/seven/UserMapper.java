package com.example.demo.mapper.seven;

import com.example.demo.pojo.seven.EmployeeProject;
import com.example.demo.pojo.seven.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 注册
     */
    void insertUser(String employeeID,String username, String password,String salt,String type);

    /**
     * 修改密码
     */
    void updatePassword(String newPassword,String username, String password);

    List<User> countType();

    void updatePassword2(@Param("password") String password,@Param("employeeID") String employeeID,@Param("salt") String salt);

    User selectEmployeeIDByUsername(String username);

    /**
     * 根据用户名密码查找用户表（登录）
     */
    User selectUser(String employeeID);

    User login(String employeeID,String password);

    /*根据employeeID在用户表查找用户*/
    User selectUserByEid(String employeeID);
    /*根据employeeID在员工表表查找员工*/
    User selectEmployeeByEid(String employeeID);



}
