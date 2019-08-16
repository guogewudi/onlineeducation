package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
//mapper继承了basemapper，从而可以和数据库映射
    //serviceimpl继承了mapper和实体类封装而成的map，从而serivce也可以调用和mapper相关的对数据库的操作
    //但是，方法名称被mybatisplus改变了
    //实验证明 service接口继承了IService()同时它的实现类继承了ServiceImpl<Mapper, Object>，这样实现类的对象才可以
    //实现和Mapper一样的查询功能
    @Autowired
    private TeacherMapper teacherMapper;


    /**

     * @author Guoyuhang
     * @since 2019-08-15
     */
    @GetMapping
    //查询 返回json数据
    @ApiOperation(value = "所有讲师列表")
    public R list(){

        List<Teacher> list = teacherService.list(null);
        if(list!=null) {
            R r = R.ok().data("items", list);
            return r;
        }else {
            R error = R.error();
            return error;
        }
    }

    /**

     * @author Guoyuhang
     * @since 2019-08-15
     */

    @DeleteMapping("{id}")
//删除
    @ApiOperation(value = "根据ID删除讲师")
    public R removeById(
            @ApiParam(name = "idea",value = "讲师ID",required = true)//required表示必填
            @PathVariable String id){

        boolean b = teacherService.removeById(id);
        if(b==true) {
            R ok = R.ok();
            return ok;
        }else{
            R error = R.error();
            return error;
        }

    }

    /**

     * @author Guoyuhang
     * @since 2019-08-15
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<Teacher> pageParam = new Page<>(page, limit);
//
        IPage<Teacher> page1 = teacherService.page(pageParam, null);
        List<Teacher> records = page1.getRecords();
        long total = page1.getTotal();
//        IPage<Teacher> teacherIPage = teacherMapper.selectPage(pageParam, null);
//        List<Teacher> records = teacherIPage.getRecords();
//        long total = teacherIPage.getTotal();
        return  R.ok().data("total", total).data("rows", records);
        }

}
