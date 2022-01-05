package com.cjx.server.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("流程定义条件请求类")
public class ProcDefiREQ extends BaseRequest{

    @ApiModelProperty("流程名称")
    private String name;

    @ApiModelProperty("标识key")
    private String key;


}
