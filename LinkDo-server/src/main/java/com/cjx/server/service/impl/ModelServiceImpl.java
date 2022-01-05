package com.cjx.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.pojo.req.ModelAddREQ;
import com.cjx.server.service.IModelService;
import com.cjx.server.utils.DateUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl extends ActivitiService implements IModelService {

    /**
     * 新增模型基本信息
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public RespBean add(ModelAddREQ req) throws Exception {
        //初始化版本号
        int version = 0;

        // 1. 初始空的模型
        Model model = repositoryService.newModel();
        model.setName(req.getName());
        model.setKey(req.getKey());
        model.setVersion(version);

        // 封装模型json对象
        ObjectNode objectNode  = objectMapper.createObjectNode();
        objectNode.put(ModelDataJsonConstants.MODEL_NAME, req.getName());
        objectNode.put(ModelDataJsonConstants.MODEL_REVISION, version);
        objectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, req.getDescription());
        model.setMetaInfo(objectNode.toString());
        // 保存初始化的模型基本信息数据
        repositoryService.saveModel(model);

        // 封装模型对象基础数据json串
        // {"id":"canvas","resourceId":"canvas","stencilset":{"namespace":"http://b3mn.org/stencilset/bpmn2.0#"},"properties":{"process_id":"未定义"}}
        ObjectNode editorNode = objectMapper.createObjectNode();
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.replace("stencilset", stencilSetNode);
        // 标识key
        ObjectNode propertiesNode = objectMapper.createObjectNode();
        propertiesNode.put("process_id", req.getKey());
        editorNode.replace("properties", propertiesNode);

        repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));

        return RespBean.success("添加成功!" , model.getId());
    }

    /**
     * 条件分页查询流程定义模型
     * @param req
     * @return
     */
    @Override
    public RespBean getModelList(ModelAddREQ req) {
        ModelQuery query = repositoryService.createModelQuery();
        if (StringUtils.isNotEmpty(req.getName())){
            query.modelNameLike("%" + req.getName() + "%");
        }
        if (StringUtils.isNotEmpty(req.getKey())){
            query.modelKey(req.getKey());
        }
        //创建时间降序排列
        query.orderByCreateTime().desc();
        //分页查询
        List<Model> modelList = query.listPage(req.getFirstResult(), req.getSize());
        //总记录
        long total = query.count();

        List<Map<String, Object>> records = new ArrayList<>();
        for (Model model : modelList) {
            Map<String, Object> data = new HashMap<>();
            data.put("key" , model.getKey());
            data.put("id" , model.getId());
            data.put("name" , model.getName());
            data.put("version" , model.getVersion());
            //封装描述信息
            String desc = JSONObject.parseObject(model.getMetaInfo()).getString(ModelDataJsonConstants.MODEL_DESCRIPTION);
            data.put("description" , desc);
            data.put("createDate" , DateUtils.format(model.getCreateTime()));
            data.put("updateDate" , DateUtils.format(model.getLastUpdateTime()));
            records.add(data);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        return RespBean.success("查询成功!" , result);
    }
}
