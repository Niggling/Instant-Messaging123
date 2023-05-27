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
public class Project implements Serializable {

    private String name;

    private String projectID;

    private String bigclass;

    private String smallclass;

    private String progress;

    private String money;

    private String importance;

    private String stage;

    private String fact_end_time;

    private String start_time;

    private String expect_end_time;

    private String teamname;

    private String project_outline;

    private String del;

    private String finish;
    //ep
    private String leader;

    private String employeeID;

}
