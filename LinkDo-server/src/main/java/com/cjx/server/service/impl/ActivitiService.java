package com.cjx.server.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 统一管理activiti提供的服务接口
 */
public class ActivitiService {

    @Resource
    public ObjectMapper objectMapper;

    @Resource
    public RepositoryService repositoryService;

    @Resource
    public RuntimeService runtimeService;

    @Resource
    public TaskService taskService;

    @Resource
    public HistoryService historyService;

    /**
     * 用户要拥有角色 ACTIVITI_USER
     */
    @Resource
    public ProcessRuntime processRuntime;

    /**
     * 用户要拥有角色 ACTIVITI_USER
     */
    @Resource
    public TaskRuntime taskRuntime;

}
