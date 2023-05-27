package com.example.demo.mapper.seven;

import com.example.demo.pojo.seven.Employee;

public interface EmployeeMapper {

    /**
     * 添加新员工
     */
    boolean insertEmployee(Employee insert);



    /**
     * 修改项目信息
     */

    void updateProject();

    /**
     * 根据id查询项目信息
     */
    Employee selectEmployeeById(String employeeID);

    Employee selectEmployeeidByname(String employeeID);

    Employee selectMailById(String employeeID);

    boolean updateEmployee(Employee updated);

    /**
     * 删除项目信息
     */
    void deleteProject();


}
