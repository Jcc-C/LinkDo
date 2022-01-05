package com.cjx.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cjx.server.pojo.Employee;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.pojo.RespPageBean;
import com.cjx.server.pojo.Salary;
import com.cjx.server.service.IEmployeeService;
import com.cjx.server.service.ISalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工帐套
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Resource
    private ISalaryService salaryService;
    @Resource
    IEmployeeService employeeService;


    @ApiOperation(value = "获取所有工资帐套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工帐套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage , @RequestParam(defaultValue = "10")Integer size){
        return employeeService.getEmployeeWithSalary(currentPage , size);
    }

    @ApiOperation(value = "更新员工帐套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid , Integer sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId" , sid).eq("id" , eid))){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
