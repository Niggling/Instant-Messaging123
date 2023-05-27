package com.example.demo.pojo.seven;

import lombok.Data;


@Data
public class Employee {

//    MyString employeeID = new MyString(1);

    private String employeeID;

    private String name;

    private String sex;

    private String phone;

    private String email;

    private String address;

    public boolean slt(){
        return this == null ? false : true;
    }

}