package com.example.demo.pojo.help;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 宁立
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange implements Serializable {

    private String employeeID;//老员工

    private String projectID;

    private String eid;//项目主管

    //private String pid;//projectID

    //private String jobstage;

    //private String job;

    // private String teamname;

    // private String pictureNum;

    // private String name;

}
