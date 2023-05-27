package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.seven.Outline;
import com.example.demo.service.OutlineService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(path = "/outline")
public class OutlineController {

    @Autowired
    private OutlineService outlineService;
    
    //放入回收站或恢复
    @PostMapping("/del/update")
    @ResponseBody
    public String updateDel(@RequestBody Outline outline)  throws SQLException, Exception{
        return outlineService.updateOutline(outline);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addOutline(@RequestBody Outline outline) throws SQLException, Exception {
        return outlineService.addOutline(outline);
    }

    @PostMapping("/find/outlineID")
    @ResponseBody
    public String selectByOid(@RequestBody Outline outline)  throws SQLException, Exception{
        return outlineService.getOutlineById(outline);
    }

    @GetMapping("/find/all")
    public List<Outline> allOutline()  throws SQLException, Exception{
        return outlineService.getAllOutlines();
    }
}
