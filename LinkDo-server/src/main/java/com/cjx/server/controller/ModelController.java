package com.cjx.server.controller;

import com.cjx.server.pojo.RespBean;
import com.cjx.server.pojo.req.ModelAddREQ;
import com.cjx.server.service.IModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/model")
public class ModelController {

    @Resource
    private IModelService modelService;


    @ApiOperation(value = "新增模型基本信息")
    @PostMapping("/")
    public RespBean add(@RequestBody ModelAddREQ req){
        try {
            return modelService.add(req);
        } catch (Exception e){
            e.printStackTrace();
            log.error("创建模型失败: " + e.getMessage());
            return RespBean.error("创建模型失败!");
        }
    }

    @ApiOperation(value = "分页查询流程定义模型")
    @PostMapping("/list")
    public RespBean modelList(@RequestBody ModelAddREQ req){
        try {
            return modelService.getModelList(req);
        } catch (Exception e){
            e.printStackTrace();
            log.error("分页查询流程定义模型: " + e.getMessage());
            return RespBean.error("查询列表失败!");
        }
    }

}
