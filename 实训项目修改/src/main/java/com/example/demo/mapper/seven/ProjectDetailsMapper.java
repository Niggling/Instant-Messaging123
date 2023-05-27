package com.example.demo.mapper.seven;

import com.example.demo.pojo.seven.ProjectDetails;
import org.apache.ibatis.annotations.Param;

public interface ProjectDetailsMapper {

    /**
     * Mybatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中的sql语句的id要和mapper接口中的方法名保持一致
     */

    /**
     * 添加项目信息
     */
    int insertProjectDetails(String projectID,String name,@Param("bigclass")String bigclass,String smallclass,String progress,String money,String importance, String stage,String finish,String leader,String teamname);
    //SELECT MAX(employeeID) FROM project_management.employee;
//    String project_outline,String del
    int insertProjectTime(String projectID,String start_time,String expect_end_time,String fact_end_time);
    int insertProjectOutline(String projectID,String project_outline,String del,String name);

    int insertProjectE_P(String employeeID,String projectID,String job,String jobstage);

    /**
     * 修改项目信息
     */

    void updateProject();

    /**
     * 根据id查询项目信息
     */
    ProjectDetails selectProjectById(String projectID);

    ProjectDetails selectProjectByName(String name);

    ProjectDetails  selectProjectName(String name);



    void updateProject(String progress, String money, String importance, String stage, String projectID);//
//    public Login selectByCondition(@param("name")String username,@param("pwd")String password);
    void updateProjectTime(@Param("fact_end_time") String fact_end_time,@Param("projectID") String projectID );//@param("fact_end_time")




    /**
     * 删除项目信息
     */
    void deleteProjectdetails(String projectID);
    void deleteProjectoutline(String projectID);
    void deleteProjecttime(String projectID);
    void deleteProjectemployee_project(String projectID);

    void updateFinish(String finish,String projectID);

    void updateFinish2(String finish,String projectID,String fact_end_time);

    void updateFinish3(String projectID,String fact_end_time);


}
