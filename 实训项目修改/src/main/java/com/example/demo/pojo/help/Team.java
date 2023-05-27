package com.example.demo.pojo.help;

import lombok.Data;

/**
 * @author 宁立
 * @version 1.0
 */
@Data
public class Team {
    
    private String employeeID;

    private String projectID;

    private String jobstage;

    private String job;
//
//    private String teamname;

/*    private String pictureNum;*/

    private String name;

    public boolean slt(){
        return this == null ? false : true;
    }
}
