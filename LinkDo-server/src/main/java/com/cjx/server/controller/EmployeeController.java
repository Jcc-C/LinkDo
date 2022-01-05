package com.cjx.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.cjx.server.pojo.*;
import com.cjx.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IPoliticsStatusService politicsStatusService;
    @Resource
    private IJoblevelService joblevelService;
    @Resource
    private INationService  nationService;
    @Resource
    private IPositionService positionService;
    @Resource
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1")Integer currentPage , @RequestParam(defaultValue = "10")Integer size , Employee employee , LocalDate[] beginDate){
        return employeeService.getEmployeeByPage(currentPage , size , employee , beginDate);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getPolicaticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    /**
     * 有层级关系，不能直接使用list
     * @return
     */
    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID(){
        return employeeService.maxWorkID();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    /**
     * 添加员工，不考虑合同期限（合同不可修改)
     * @param employee
     * @return
     */
    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmployee(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "导出员工数据")
    //用流的形式输出
    @GetMapping(value = "/export" , produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        //获取所有员工数据
        List<Employee> list = employeeService.getEmployee(null);
        //ExcelType.HSSF - Office2003版
        ExportParams params = new ExportParams("员工表" , "员工表" , ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type" , "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition" , "attachment;filename=" + URLEncoder.encode("员工表.xls" , "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile multipartFile){
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> jobLevelList = joblevelService.list();
        List<Position> positionList = positionService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(multipartFile.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                //重写HashCode方法;导入的时候需要获取实体类的id进行插入,但表格中是名字
                //民族id
                Integer NationId = nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId();
                employee.setNationId(NationId);

                //政治面貌
                Integer politicsStatusId = politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId();
                employee.setPoliticId(politicsStatusId);

                //部门
                Integer departmentId = departmentList.get(departmentList.indexOf(new Department(employee.getDepartment().getName()))).getId();
                employee.setDepartmentId(departmentId);

                //职称
                Integer jobLevelId = jobLevelList.get(jobLevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId();
                employee.setJobLevelId(jobLevelId);

                //职位
                Integer posId = positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId();
                employee.setPosId(posId);
            });
            if (employeeService.saveBatch(list)){
                return RespBean.success("导入成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败!");
    }

}
