package com.cjx.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjx.server.pojo.Department;
import com.cjx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     */
    void addDepartment(Department department);

    /**
     * deleteDepartment
     * @param department
     * @return
     */
    RespBean deleteDepartment(Department department);
}
