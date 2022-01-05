package com.cjx.server.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增模型请求类")
public class ModelAddREQ extends ModelREQ {

    @ApiModelProperty("描述")
    private String description;

}
