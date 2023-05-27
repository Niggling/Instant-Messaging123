package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.mapper.help.MyTeamMapper;
import com.example.demo.mapper.help.TeamMapper;
import com.example.demo.pojo.help.MyTeam;
import com.example.demo.pojo.help.Team;
import com.example.demo.utils.SqlSessionUtils;

@Service(value = "teamService")
@SuppressWarnings("all")
@Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT)
public class TeamService {
    
    public List<MyTeam> findTeam(MyTeam myTeam)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        //获取mapper接口对象
        MyTeamMapper mapper = sqlSession.getMapper(MyTeamMapper.class);
        return mapper.selectAllTeam(myTeam.getEmployeeID());
    }

    public List<Team> getAllMembersInTeam(Team team)  throws SQLException, Exception{
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        TeamMapper mapper = sqlSession.getMapper(TeamMapper.class);
        return mapper.selectAllMember(team.getProjectID());
    }

}
