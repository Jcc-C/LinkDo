package com.cjx.server.mapper;

import com.cjx.server.pojo.AttendanceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考勤 Mapper 接口
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {

    /**
     * 签退
     * @param id
     * @return
     */
    List<AttendanceRecord> clockOut(Long id);

    /**
     * 签到
     * @param attendanceRecord
     * @return
     */
    int clockIn(AttendanceRecord attendanceRecord);

    /**
     * 查询签到记录
     * @param userId
     * @param year
     * @param month
     * @param day
     * @return
     */
    List<AttendanceRecord> listByUserIdAndClockDate(@Param("userId") Integer userId, @Param("year") Integer year,
    @Param("month") Integer month, @Param("day") Integer day);

    /**
     * 签退
     * @param attendanceRecord
     * @return
     */
//    int clockOut(AttendanceRecord attendanceRecord);

}
