package com.example.demo.pojo.seven;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails implements Serializable {

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

    private String project_outline;

    private String del;

    private String finish;
//ep
    private String leader;

    private String teamname;

    private String employeeID;//项目建立人的

    private String job;

    private String jobstage;

    public boolean slt() {
        return this == null ? false : true;
    }

    public String getProjectname() {
        return name;
    }
}