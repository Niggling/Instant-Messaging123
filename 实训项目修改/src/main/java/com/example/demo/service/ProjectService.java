package com.example.demo.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.help.ProjectMapper;
import com.example.demo.mapper.seven.ProjectDetailsMapper;
import com.example.demo.pojo.help.Project;
import com.example.demo.pojo.seven.ProjectDetails;
import com.example.demo.utils.SqlSessionUtils;

@Service(value = "projectService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class)
public class ProjectService {

    public String updateProject(ProjectDetails projectDetails)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
        // try {
        mapper.updateProject(projectDetails.getProgress(), projectDetails.getMoney(), projectDetails.getImportance(),
                projectDetails.getStage(), projectDetails.getProjectID());//
        // mapper.updateProjectTime(project.getFact_end_time(), project.getProjectID());
        return "已修改";
    }

    public String deleteProject(ProjectDetails project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
        mapper.deleteProjectoutline(project.getProjectID());
        mapper.deleteProjecttime(project.getProjectID());
        mapper.deleteProjectemployee_project(project.getProjectID());
        mapper.deleteProjectdetails(project.getProjectID());
        return "已彻底删除";
    }

    public String addProject(ProjectDetails project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
//        Project_details maxPID = mapper.selectMaxProjectID();
        ProjectDetails na = mapper.selectProjectName(project.getName());//项目名称不可重复
        if(na==null) {
            mapper.insertProjectDetails(project.getProjectID(), project.getName(), project.getBigclass(), project.getSmallclass(),
                    project.getProgress(), project.getMoney(), project.getImportance(), project.getStage(), project.getFinish(), project.getLeader()
                    , project.getTeamname());
            mapper.insertProjectOutline(project.getProjectID(), project.getProject_outline(), project.getDel(), project.getName());
            mapper.insertProjectTime(project.getProjectID(), project.getStart_time(), project.getExpect_end_time(), project.getFact_end_time());
            mapper.insertProjectE_P(project.getEmployeeID(), project.getProjectID(), project.getJob(), project.getJobstage());
            return "成功新增项目";
        }
        else return "重名";
    }

    public List<Project> findProjectIng()  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectProjectIng();
    }

    public List<Project> searchAllProjectsFinished(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.searchAllProjectFinish(project.getName());
    }

    public List<Project> searchAllProjectsIng(Project project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.searchAllProjectIng(project.getName());
    }

    public List<Project> findAllProjectsFinished()  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        ProjectMapper mapper = sqlSession.getMapper(ProjectMapper.class);
        return mapper.selectProjectFinish();
    }

    public String updateFinish(ProjectDetails project)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ProjectDetailsMapper mapper = sqlSession.getMapper(ProjectDetailsMapper.class);
        if(project.getFinish().equals("0"))//恢复
        {
            mapper.updateFinish(project.getFinish(), project.getProjectID());
            mapper.updateFinish3(project.getProjectID(),null);
            return "已恢复";
        }
        else if (project.getFinish().equals("1"))
        {
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(df.format(day));
            mapper.updateFinish2(project.getFinish(), project.getProjectID(),project.getFact_end_time());
            mapper.updateFinish3(project.getProjectID(),df.format(day));
            return "已结项";
        }
        else return "输入错误";
    }
}
