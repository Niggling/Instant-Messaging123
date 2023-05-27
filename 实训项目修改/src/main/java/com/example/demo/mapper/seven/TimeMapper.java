package com.example.demo.mapper.seven;

import com.example.demo.pojo.seven.Time;

public interface TimeMapper {

    /**
     * Mybatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中的sql语句的id要和mapper接口中的方法名保持一致
     */

    /**
     * 添加项目信息
     */
    int insertProject();

    /**
     * 修改项目信息
     */

    void updateProject();

    /**
     * 根据id查询项目信息
     */
    Time selectTimeById(String projectID);

    /**
     * 删除项目信息
     */
    void deleteProject();


}
