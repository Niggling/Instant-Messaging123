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
import com.example.demo.mapper.seven.EmployeeMapper;
import com.example.demo.pojo.seven.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.SqlSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    //修改员工个人信息
    @PostMapping("/update")
    @ResponseBody
    public String updateEmployee(@RequestBody Employee updatedEmployee) throws SQLException, Exception {
        return employeeService.updateEmployee(updatedEmployee);
    }
    //根据employeeID查找员工表（个人中心显示员工信息）
    @PostMapping("/find")
    @ResponseBody
    public Employee selectByID(@RequestBody Employee employee) throws SQLException, Exception {
        return employeeService.getEmployeeById(employee);
    }

    //个人中心
    //hr聘请公司新员工时在数据库里加employee
    @PostMapping("/add")
    @ResponseBody
    public String insertEmployee(@RequestBody Employee insertEmployee) throws SQLException, Exception {
        return employeeService.insertEmployee(insertEmployee);
    }

    @PostMapping("/name/find")
    @ResponseBody
    public String selectByName(@RequestBody Employee employee)  throws SQLException, Exception{
        return employeeService.findEmployeeByName(employee);
    }

}
