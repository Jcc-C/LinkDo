package com.cjx.server.service;

import com.cjx.server.pojo.AttendanceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 * 考勤 服务类
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
public interface IAttendanceRecordService extends IService<AttendanceRecord> {

    /**
     * 签退
     * @param id
     * @return
     */
    RespBean clockOut(Integer id);

    /**
     * 签到
     * @param userId
     * @return
     */
    RespBean clockIn(Integer userId);

    /**
     * 查询签到记录
     * @param userId
     * @param year
     * @param month
     * @param day
     * @return
     */
    List<AttendanceRecord> listByUserIdAndClockDate(Integer userId, Integer year, Integer month, Integer day);
}
