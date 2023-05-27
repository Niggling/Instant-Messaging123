package com.example.demo.pojo.seven;

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
public class EmployeeProject implements Serializable {

    private String employeeID;

    private String projectID;

    private String jobstage;

    private String job;

    public boolean slt(){
        return this == null ? false : true;
    }


}
