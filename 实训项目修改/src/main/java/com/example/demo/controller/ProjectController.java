package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.help.Project;
import com.example.demo.pojo.seven.ProjectDetails;
import com.example.demo.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    //在途，结项项目
    //根据pid查找并编辑项目
    @PostMapping("/update")
    @ResponseBody
    public String updateProject(@RequestBody ProjectDetails project)  throws SQLException, Exception{
        return projectService.updateProject(project);
    }

    //完全删除项目
    @PostMapping("/delete")
    @ResponseBody
    public String deleteProject(@RequestBody ProjectDetails project)  throws SQLException, Exception{
        return projectService.deleteProject(project);
    }

    //新增项目
    @PostMapping("/add")
    @ResponseBody
    public String addProject(@RequestBody ProjectDetails project) throws SQLException, Exception {//新增项目
        return projectService.addProject(project);
    }

    @GetMapping("/find/ing")
    public List<Project> ProjectIng()  throws SQLException, Exception{
        return projectService.findProjectIng();
    }

    //按名搜索所有已结项项目
    @PostMapping("/search/finish")
    @ResponseBody
    public List<Project> SearchAllProjectFinish(@RequestBody Project project) throws SQLException, Exception {
        return projectService.searchAllProjectsFinished(project);
    }

    //按名搜索所有在途项目
    @PostMapping("/search/Ing")
    @ResponseBody
    public List<Project> SearchAllProjectIng(@RequestBody Project project)  throws SQLException, Exception{
        return projectService.searchAllProjectsIng(project);
    }

    //查找所有已结项项目
    @GetMapping("/find/finish")
    public List<Project> ProjectFinish()  throws SQLException, Exception{
        return projectService.findAllProjectsFinished();
    }

    //项目结项调整
    @PostMapping("/finish/update")
    @ResponseBody
    public String updateFinish(@RequestBody ProjectDetails project)  throws SQLException, Exception{
        return projectService.updateFinish(project);
    }

}
