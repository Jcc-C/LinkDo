package com.cjx.server.controller;


import com.cjx.server.pojo.AttendanceRecord;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IAttendanceRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 考勤 前端控制器
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceRecordController {

    @Resource
    private IAttendanceRecordService attendanceRecordService;

    @ApiOperation(value = "获取签到记录")
    @GetMapping("/record/")
    public List<AttendanceRecord> listAttendance(@RequestParam(value = "userId") Integer userId  , @RequestParam(value = "year") Integer year ,
                                                 @RequestParam(value = "month") Integer month , Integer day){
        return attendanceRecordService.listByUserIdAndClockDate(userId , year , month , day);
    }

    @ApiOperation(value = "签到")
    @PostMapping("/record/")
    public RespBean clockIn(@RequestParam Integer userId){
            return attendanceRecordService.clockIn(userId);
    }


    @ApiOperation(value = "签退")
    @PutMapping("/record/")
    public RespBean clockOut(@RequestParam Integer id){
            return attendanceRecordService.clockOut(id);
        }
    @ApiOperation(value = "用于考勤记录页面")
    @GetMapping("/view/")
    public List<AttendanceRecord> listAttendanceForRecord(@RequestParam(value = "userId") Integer userId  , @RequestParam(value = "year") Integer year ,
                                                 @RequestParam(value = "month") Integer month , Integer day){
        return attendanceRecordService.listByUserIdAndClockDate(userId , year , month , day);
    }

}
