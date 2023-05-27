package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.help.ProjectMapper;
import com.example.demo.mapper.seven.EmployeeProjectMapper;
import com.example.demo.pojo.help.Project;
import com.example.demo.pojo.seven.EmployeeProject;
import com.example.demo.utils.SqlSessionUtils;

@Service(value = "myProjectService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT)
public class MyProjectService {
    
    public List<Project> getMyProjectsIng(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectMyProjectIng(project.getEmployeeID());
    }

    public List<Project> getFinishedProjectsOfMy(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectMyProjectFinish(project.getEmployeeID());
    }

    public List<Project> getProjectsBySearch(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.searchProjectFinish(project.getEmployeeID(),project.getName());
    }

    public List<Project> getProjectsInBin(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectProjectRubbish(project.getEmployeeID());
    }

    public List<Project> searchMyProjectsInBin(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.searchProjectRubbish(project.getEmployeeID(),project.getName());
    }

    public List<Project> getAllProjects(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectProject(project.getEmployeeID());
    }

    public String getMyTeamProject(EmployeeProject employeeProject)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        return mapper.selectJobStage(employeeProject.getEmployeeID(),employeeProject.getProjectID()).getJobstage();
    }
}
