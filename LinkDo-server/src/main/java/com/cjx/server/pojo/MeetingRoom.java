package com.cjx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 会议室表
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_meeting_room")
@ApiModel(value="MeetingRoom对象", description="会议室表")
public class MeetingRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会议室名称")
    private String name;

    @ApiModelProperty(value = "最大人数")
    private Integer max;

    @ApiModelProperty(value = "备注")
    private String desc;

    @ApiModelProperty(value = "状态，0不可用，1可用")
    private Integer status;


}
