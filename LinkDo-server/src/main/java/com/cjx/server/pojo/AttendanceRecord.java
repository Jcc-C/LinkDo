package com.cjx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考勤
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_attendance_record")
@ApiModel(value="AttendanceRecord对象", description="考勤")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "打卡日期")
    @TableField("clock_date")
    private LocalDate clockDate;

    @ApiModelProperty(value = "签到时间")
    @TableField("clock_in_time")
    private LocalTime clockInTime;

    @ApiModelProperty(value = "签退时间")
    @TableField("clock_out_time")
    private LocalTime clockOutTime;

    @ApiModelProperty(value = "迟到分钟数")
    @TableField("come_late_minutes")
    private Integer comeLateMinutes;

    @ApiModelProperty(value = "早退分钟数")
    @TableField("leave_early_minutes")
    private Integer leaveEarlyMinutes;


}
