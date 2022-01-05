package com.cjx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会议表
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_meeting")
@ApiModel(value="Meeting对象", description="会议表")
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "UUID")
    private String uuid;

    @ApiModelProperty(value = "会议题目")
    private String title;

    @ApiModelProperty(value = "创建人ID")
    @TableField("creator_id")
    private Long creatorId;

    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "开会地点")
    private String place;

    @ApiModelProperty(value = "开始时间")
    private LocalTime start;

    @ApiModelProperty(value = "结束时间")
    private LocalTime end;

    @ApiModelProperty(value = "会议类型（1在线会议，2线下会议）")
    private Integer type;

    @ApiModelProperty(value = "参与者")
    private String members;

    @ApiModelProperty(value = "会议内容")
    private String desc;

    @ApiModelProperty(value = "工作流实例ID")
    @TableField("instance_id")
    private String instanceId;

    @ApiModelProperty(value = "出席人员名单")
    private String present;

    @ApiModelProperty(value = "未出席人员名单")
    private String unpresent;

    @ApiModelProperty(value = "状态（1.申请中，2.审批未通过，3.审批通过，4.会议进行中，5.会议结束）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
