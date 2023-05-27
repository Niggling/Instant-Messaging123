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
public class Job implements Serializable {

    private int value;

    private String name;
    
}
