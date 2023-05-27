package com.example.demo.pojo.seven;


import lombok.Data;

//用户：用户名(VARCHAR)（员工编号），密码(String)（加密）------（补充：注册）
@Data
public class User {

    private String username;

    private String employeeID;

    private String password;
//
//    private String newPassword;//新密码或确认密码，数据库不含该属性

    private String salt;//加密

    private String type;

    public boolean slt(){
        return this == null ? false : true;
    }//查到为true

}
