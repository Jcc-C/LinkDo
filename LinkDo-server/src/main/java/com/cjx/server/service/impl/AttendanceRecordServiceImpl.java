package com.cjx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.server.exception.ParamsException;
import com.cjx.server.mapper.AttendanceRecordMapper;
import com.cjx.server.mapper.AttendanceSettingMapper;
import com.cjx.server.pojo.AttendanceRecord;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IAttendanceRecordService;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 考勤 服务实现类
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
@Service
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements IAttendanceRecordService {

    @Resource
    private AttendanceRecordMapper attendanceRecordMapper;
    @Resource
    private AttendanceSettingMapper attendanceSettingMapper;


    /**
     * 签退
     * @param id
     * @return
     */
    @Override
    public RespBean clockOut(Integer id) {
        AttendanceRecord attendance = attendanceRecordMapper.selectById(id);
        if (attendance == null){
            return RespBean.error("今天还没有签到过噢!");
        }
        if (attendance.getClockOutTime() != null){
            return RespBean.error("今天已经签退过了噢!");
        }

        String workingHoursEnd = attendanceSettingMapper.selectSettingValueByName("working_hours_end");
        Duration duration = Duration.between(LocalTime.now(), LocalTime.parse(workingHoursEnd));
        long leaveEarlyMinutes = duration.toMinutes();
        if (leaveEarlyMinutes > 0){
            attendance.setLeaveEarlyMinutes((int) leaveEarlyMinutes);
        }
        attendance.setClockOutTime(LocalTime.now());
        if (attendanceRecordMapper.updateById(attendance) == 1){
            if (leaveEarlyMinutes > 0){
                return RespBean.success("签退成功,今天辛苦啦!但是您今天早退" + leaveEarlyMinutes + "分钟噢");
            }
            return RespBean.success("签退成功,今天辛苦啦!" , attendance);
        }
        return RespBean.error("签退失败,请重试!");

    }

    /**
     * 签到
     * @param userId
     * @return
     */
    @Override
    public RespBean clockIn(Integer userId) {

        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        if (attendanceRecordMapper.listByUserIdAndClockDate(userId , year , month , day).size() > 0){
            return RespBean.error("今天已签到!");
        }
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setUserId(userId);
        String workingHoursStart = attendanceSettingMapper.selectSettingValueByName("working_hours_start");
        Duration duration = Duration.between(LocalTime.parse(workingHoursStart), LocalDateTime.now());
        long comeLateMinutes = duration.toMinutes();
        if (comeLateMinutes > 0){
            attendanceRecord.setComeLateMinutes((int) comeLateMinutes);
        }
        attendanceRecord.setClockDate(LocalDate.now());
        attendanceRecord.setClockInTime(LocalTime.now());
        if (attendanceRecordMapper.clockIn(attendanceRecord) == 1 ){
            if (comeLateMinutes > 0 ){
                return RespBean.success("签到成功!但是您迟到了" + comeLateMinutes + "分钟!");
            }
            return RespBean.success("签到成功!" , attendanceRecord);
        }
        return RespBean.error("签到失败!");
    }

    /**
     * 查询签到记录
     * @param userId
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Override
    public List<AttendanceRecord> listByUserIdAndClockDate(Integer userId, Integer year, Integer month, Integer day) {
        return attendanceRecordMapper.listByUserIdAndClockDate(userId , year , month , day);
    }
}
