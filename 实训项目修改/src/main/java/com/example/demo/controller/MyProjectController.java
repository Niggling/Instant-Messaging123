package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.mapper.seven.EmployeeProjectMapper;
import com.example.demo.pojo.help.Project;
import com.example.demo.pojo.seven.EmployeeProject;
import com.example.demo.service.MyProjectService;
import com.example.demo.utils.SqlSessionUtils;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/MyProject")
public class MyProjectController {
    
    @Autowired
    private MyProjectService myProjectService;

    @PostMapping("/all")
    @ResponseBody
    public List<Project> MyProject(@RequestBody Project project)  throws SQLException, Exception{
        return myProjectService.getAllProjects(project);
    }

    @PostMapping("/find/ing")
    @ResponseBody
    public List<Project> MyProjectIng(@RequestBody Project project) throws SQLException, Exception {
        return myProjectService.getMyProjectsIng(project);
    }

    @PostMapping("/find/finish")
    @ResponseBody
    public List<Project> MyProjectFinish(@RequestBody Project project)  throws SQLException, Exception{
        return myProjectService.getFinishedProjectsOfMy(project);
    }

    //按名搜索结项项目
    @PostMapping("/search/finish")
    @ResponseBody
    public List<Project> SearchProjectFinish(@RequestBody Project project)  throws SQLException, Exception{
        return myProjectService.getProjectsBySearch(project);
    }

    //查找所有回收站项目
    @PostMapping("/find/Rubbish")
    @ResponseBody
    public List<Project> MyProjectRubbish(@RequestBody Project project) throws SQLException, Exception {
        return myProjectService.getProjectsInBin(project);
    }

    @PostMapping("/search/Rubbish")
    @ResponseBody
    public List<Project> SearchMyProjectRubbish(@RequestBody Project project) throws SQLException, Exception {
        return myProjectService.searchMyProjectsInBin(project);
    }

    @PostMapping("/findJobstage")
    @ResponseBody
    public String findTeam(@RequestBody EmployeeProject ep) throws SQLException, Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        return mapper.selectJobStage(ep.getEmployeeID(),ep.getProjectID()).getJobstage();
    }

}
