package com.example.demo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.seven.EmployeeMapper;
import com.example.demo.mapper.seven.EmployeeProjectMapper;
import com.example.demo.pojo.seven.Employee;
import com.example.demo.pojo.seven.EmployeeProject;
import com.example.demo.utils.SqlSessionUtils;

import java.sql.SQLException;

@Service(value = "employeeService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT)
public class EmployeeService {
    
    public String getTeam(EmployeeProject employeeProject)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        return mapper.selectJobStage(employeeProject.getEmployeeID(),employeeProject.getProjectID()).getJobstage();
    }

    public Employee getEmployeeById(Employee employee) throws SQLException, Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);//找mapper
        Employee e = mapper.selectEmployeeById(employee.getEmployeeID());
        return e;
    }

    public String updateEmployee(Employee employee) throws SQLException, Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        boolean e = mapper.updateEmployee(employee);
        if (e) {
            return "修改员工信息成功";
        } else {
            return "修改员工信息失败";
        }
    }

    public String insertEmployee(Employee employee)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        try {
            mapper.insertEmployee(employee);
        } catch (Exception e) {
            return "插入员工信息失败";
        }
        return "插入员工信息成功";
    }

    public String findEmployeeByName(Employee employee)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);//找mapper
        Employee hello = mapper.selectEmployeeidByname(employee.getName());
        return hello.getEmployeeID();
    }
}
