package com.example.demo.pojo.help;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author 宁立
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTeam implements Serializable {

    private String employeeID;

    private String projectID;

    private String teamName;

    private String name;//项目名称

    public boolean slt(){
        return this == null ? false : true;
    }
}
