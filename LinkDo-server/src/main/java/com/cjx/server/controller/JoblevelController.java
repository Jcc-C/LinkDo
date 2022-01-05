package com.cjx.server.controller;


import com.cjx.server.pojo.Joblevel;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("system/basic/joblevel")
public class JoblevelController {

    @Resource
    private IJoblevelService joblevelService;


    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevel(){
        return joblevelService.list();
    }

    //职称已经用枚举在数据库中列出
    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("添加职称成功");
        }
        return RespBean.error("更新职称失败");
    }

    //职称已经用枚举在数据库中列出
    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("更新职称成功");
        }
        return RespBean.error("更新职称失败");
    }

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if (joblevelService.removeById(id)){
            return RespBean.success("删除职位成功");
        }
        return RespBean.error("删除职称失败");
    }

    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除职称成功");
        }
        return RespBean.error("批量删除职称失败");
    }



}
