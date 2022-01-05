package com.cjx.server.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询待办任务列表条件")
public class TaskREQ extends BaseRequest {

    @ApiModelProperty("任务名称")
    private String taskName;

}
