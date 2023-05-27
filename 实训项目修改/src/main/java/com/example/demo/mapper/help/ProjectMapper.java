package com.example.demo.mapper.help;

import com.example.demo.pojo.help.Project;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

    List<Project> selectProject(String employeeId);

    List<Project> selectMyProjectIng(String employeeId);

    List<Project> selectProjectIng();

    List<Project> selectMyProjectFinish(String employeeId);

    List<Project> searchProjectFinish(String employeeId,String name);

    List<Project> searchProjectIng(String employeeId,String name);

    List<Project> selectProjectFinish();

    List<Project> searchAllProjectFinish(String name);

    List<Project> searchAllProjectIng(String name);

    List<Project> selectProjectRubbish(String employeeId);

    List<Project> searchProjectRubbish(String employeeId,String name);

}