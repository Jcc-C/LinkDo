package com.cjx.server.controller;


import com.cjx.server.pojo.Department;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Resource
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    private RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    private RespBean deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }


}
