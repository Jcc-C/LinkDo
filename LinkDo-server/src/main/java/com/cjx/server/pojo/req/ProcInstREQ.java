package com.cjx.server.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("流程实例条件请求类")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcInstREQ extends BaseRequest{

    @ApiModelProperty("流程名称")
    private String processName;

    @ApiModelProperty("任务发起人")
    private String proposer;

}
