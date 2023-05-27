package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.mapper.help.ExchangeMapper;
import com.example.demo.mapper.seven.EmployeeMapper;
import com.example.demo.mapper.seven.EmployeeProjectMapper;
import com.example.demo.mapper.seven.ProjectDetailsMapper;
import com.example.demo.mapper.seven.TimeMapper;
import com.example.demo.pojo.help.Exchange;
import com.example.demo.pojo.help.Job;
import com.example.demo.pojo.help.MyTeam;
import com.example.demo.pojo.help.Team;
import com.example.demo.pojo.seven.Employee;
import com.example.demo.pojo.seven.EmployeeProject;
import com.example.demo.pojo.seven.ProjectDetails;
import com.example.demo.pojo.seven.Time;
import com.example.demo.pojo.seven.User;
import com.example.demo.service.TeamService;
import com.example.demo.utils.SqlSessionUtils;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/team")
public class TeamController {
    
    @Autowired
    private TeamService teamService;

    //查看我的团队
    @PostMapping("/find/employeeID")
    @ResponseBody
    public List<MyTeam> findTeam(@RequestBody MyTeam myTeam)  throws SQLException, Exception{
        return teamService.findTeam(myTeam);
    }

    //查找所有团队成员
    @PostMapping("/find/projectID")
    @ResponseBody
    public List<Team> findMember(@RequestBody Team team)  throws SQLException, Exception{
        return teamService.getAllMembersInTeam(team);
    }

    @PostMapping("/Self")
    @ResponseBody
    public String deleteMember(@RequestBody EmployeeProject ep)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        mapper.deleteMe(ep.getEmployeeID(), ep.getProjectID());
        return "自己已离队";
    }

    //团队成员被邀请入队
    @PostMapping("/invite/teamMember")
    @ResponseBody
    public String addTeamMember(@RequestBody EmployeeProject ep) throws SQLException, Exception {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        try {
            mapper.addTeamMember(ep);
        } catch (Exception e) {
            return "该成员已经在团队中了";
        }
        return "已入队";
    }

    //踢出团队成员
    @PostMapping("/TeamMember")
    @ResponseBody
    public String deleteFire(@RequestBody EmployeeProject ep)  throws SQLException, Exception{

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        mapper.deleteOther(ep.getEmployeeID(), ep.getProjectID());
        return "已离队";
    }

    //交接
    @PostMapping("/ExchangeMember")
    @ResponseBody
    public String ExchangeMember(@RequestBody Exchange exchange)  throws SQLException, Exception{

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ExchangeMapper mapper = sqlSession.getMapper(ExchangeMapper.class);
        mapper.ExchangeMember(exchange.getEid(),exchange.getProjectID(),exchange.getEmployeeID());
        return "已交接";

    }


    //团队统计之岗位统计
    @PostMapping("/count/job")
    @ResponseBody
    public List<Job> jobCount(@RequestBody EmployeeProject ep)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        List<EmployeeProject> hi = mapper.countJob2(ep);
        Map<String, EmployeeProject> hello = mapper.countJob(ep);
        List<Job> countPackage = new ArrayList<>();
        for (String key : hello.keySet()) {
            int i = 0;
            for (EmployeeProject wow : hi) {
                if (key.equals(wow.getJob())) {
                    i++;
                }
            }
            Job job = new Job();
            job.setName(key);
            job.setValue(i);
            countPackage.add(job);
        }
        return countPackage;
    }

//    团队统计之职级统计
    @PostMapping("/count/jobstage")
    @ResponseBody
    public List<Job> typeCount(@RequestBody EmployeeProject ep)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);

        List<EmployeeProject> hello = mapper.countJobStage(ep);

        int stagecoach1 = 0;//普通员工
        int stagecoach2 = 0;//项目经理
        int stagecoach3 = 0;//项目主管
        List<Job> Jobstage = new ArrayList<>();
        for (EmployeeProject key : hello) {
            switch (key.getJobstage()) {
                case "项目员工":
                    stagecoach1++;
                    break;
                case "项目经理":
                    stagecoach2++;
                    break;
                case "项目主管":
                    stagecoach3++;
                    break;

            }
        }
        Job job1 = new Job();
        job1.setName("项目员工");job1.setValue(stagecoach1);
        Job job2 = new Job();
        job2.setName("项目经理");job2.setValue(stagecoach2);
        Job job3 = new Job();
        job3.setName("项目主管");job3.setValue(stagecoach3);
        Jobstage.add(job1);
        Jobstage.add(job2);
        Jobstage.add(job3);
        return Jobstage;
    }

//根据名字查employeeID
    @PostMapping("/name/find")
    @ResponseBody
    public String selectByName(@RequestBody Employee employee)  throws SQLException, Exception{
//        employeeID = 1;//测试
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);//找mapper
        Employee hello = mapper.selectEmployeeidByname(employee.getName());
        String eid = hello.getEmployeeID();
        return eid;
    }
    //没什么用系列

    //通过employeeID查找用户表，得到用户信息
    @PostMapping("user/find/employeeID")
    @ResponseBody
    public String selectByID(@RequestBody User user)  throws SQLException, Exception{
//        employeeID = 1;//测试
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);//找mapper
        Employee hello = mapper.selectEmployeeById(user.getEmployeeID());
        System.out.println(hello);
        return hello.toString();
    }

    //根据projectID查找项目员工表$(多了好多表里没有但是类里有的属性）
    @PostMapping("/projectID/find")
    @ResponseBody
    public String selectByPid(@RequestBody ProjectDetails project)  throws SQLException, Exception{
//        projectID = 1;
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
        ProjectDetails hello = mapper.selectProjectById(project.getProjectID());
        System.out.println(hello);
        return hello.toString();

    }

    //根据项目名查找员工项目表$
    @PostMapping("/projectName/find")
    @ResponseBody
    public String selectPByName(@RequestBody ProjectDetails project)  throws SQLException, Exception{

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
        ProjectDetails hello = mapper.selectProjectByName(project.getName());
        System.out.println(hello);
        return hello.toString();
    }

    //根据employeeID查找项目员工表
    @PostMapping("/employee_project/find/employeeID")
    @ResponseBody
    public List<EmployeeProject> selectPByEid(@RequestBody EmployeeProject ep)  throws SQLException, Exception{
//        employeeID = 1;
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeProjectMapper mapper = sqlSession.getMapper(EmployeeProjectMapper.class);
        List<EmployeeProject> hello = mapper.selectProjectByEmployeeId(ep.getEmployeeID());
        System.out.println(hello);
        return hello;

    }

    //通过项目ID查找项目时间
    @PostMapping("/time/find/projectID")
    @ResponseBody
    public String selectTimeByPid(@RequestBody Time time)  throws SQLException, Exception{
//        projectID = 1;
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        TimeMapper mapper = sqlSession.getMapper(TimeMapper.class);
        Time hello = mapper.selectTimeById(time.getProjectID());
        System.out.println(hello);
        return hello.toString();
    }

}
