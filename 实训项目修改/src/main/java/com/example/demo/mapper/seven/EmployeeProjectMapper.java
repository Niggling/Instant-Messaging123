package com.example.demo.mapper.seven;

import com.example.demo.pojo.seven.EmployeeProject;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeProjectMapper {

    /*employee_projectservice selectEmployeeByProjectId(String projectID);//根据项目查询员工*/

    List<EmployeeProject> selectProjectByEmployeeId(String employeeID);//根据employeeID查询项目员工表

    List<EmployeeProject> selectProjectIDByEmployeeId(String employeeID);//根据employeeID查询项目员工表

    EmployeeProject selectJobStage(String employeeID, String projectId);//可能不对String

    void addTeamMember(EmployeeProject member);
    List<EmployeeProject> countJobStage(EmployeeProject ep);

    @MapKey("job")
    Map<String, EmployeeProject> countJob(EmployeeProject ep);

    List<EmployeeProject> countJob2(EmployeeProject ep);


    void deleteMe(String employeeID,String projectID);

    void deleteOther(String employeeID,String projectID);
}
