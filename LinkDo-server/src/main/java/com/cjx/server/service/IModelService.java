package com.cjx.server.service;

import com.cjx.server.pojo.RespBean;
import com.cjx.server.pojo.req.ModelAddREQ;

public interface IModelService {

    /**
     * 新增模型基本信息
     * @param req
     * @return
     * @throws Exception
     */
    RespBean add(ModelAddREQ req) throws Exception;

    /**
     * 条件分页查询流程定义模型
     * @param req
     * @return
     */
    RespBean getModelList(ModelAddREQ req);
}
