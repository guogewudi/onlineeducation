package com.guli.edu.controller.admin;

import com.guli.edu.entity.Teacher;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//返回json字符串而不是页面的controller

@RequestMapping("/admin/edu/teacher")

@CrossOrigin//跨域

@Api(description="讲师管理")

public class TeacherAdminController {

    @Autowired

    private TeacherService teacherService;

    @GetMapping
    //查询 返回json数据
    @ApiOperation(value = "所有讲师列表")
    public List<Teacher> list(){
        return teacherService.list(null);
        }


    @DeleteMapping("{id}")
//删除
    @ApiOperation(value = "根据ID删除讲师")
    public boolean removeById(@PathVariable String id){

        return teacherService.removeById(id);

    }

}
