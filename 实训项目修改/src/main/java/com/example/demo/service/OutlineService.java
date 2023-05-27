package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.seven.OutlineMapper;
import com.example.demo.pojo.seven.Outline;
import com.example.demo.utils.SqlSessionUtils;

@Service(value = "outlineService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT)
public class OutlineService {
    
    public String updateOutline(Outline outline)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        OutlineMapper mapper = sqlSession.getMapper(OutlineMapper.class);
        mapper.updateDel(outline.getDel(), outline.getProjectID());
        if (outline.getDel().equals("1")) {
            return "已放入回收站";
        } else {
            return "已恢复";
        }
    }

    public String addOutline(Outline outline)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        OutlineMapper mapper = sqlSession.getMapper(OutlineMapper.class);
        //测试功能
        if (mapper.insertOutline(outline)) {
            System.out.println("项目概况添加成功");
            return "项目概况添加成功";
        } else {
            return "项目概况添加失败";
        }
    }

    public String getOutlineById(Outline outline)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        OutlineMapper mapper = sqlSession.getMapper(OutlineMapper.class);
        Outline hello = mapper.selectProjectById(outline.getProjectID());
        return hello.toString();
    }

    public List<Outline> getAllOutlines()  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        OutlineMapper mapper = sqlSession.getMapper(OutlineMapper.class);
        return mapper.selectAllOutline();
    }

}
