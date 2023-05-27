package com.example.demo.pojo.seven;

import lombok.Data;

/**
 * @author 宁立
 * @version 1.0
 */
//项目时间：项目编号(String)，开始时间(DATETIME)，预计结束时间(DATETIME)，项目实际结束时间(DATETIME)
@Data
public class Time {

    private String projectID;

    private String start_time;

    private String expect_end_time;

    private String fact_end_time;

    public boolean slt(){
        return this == null ? false : true;
    }

}