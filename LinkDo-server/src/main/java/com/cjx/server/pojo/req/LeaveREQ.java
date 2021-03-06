package com.cjx.server.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel("查询请假列表条件")
public class LeaveREQ extends BaseRequest {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("业务状态")
    private Integer status;

    @ApiModelProperty("所属的用户名")
    private String username;
}
