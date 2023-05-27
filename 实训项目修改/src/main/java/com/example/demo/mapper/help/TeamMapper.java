package com.example.demo.mapper.help;

import com.example.demo.pojo.help.Team;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper {

    List<Team> selectAllMember(String projectID);

}
