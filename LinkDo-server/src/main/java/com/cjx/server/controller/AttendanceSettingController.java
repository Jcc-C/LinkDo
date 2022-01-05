package com.cjx.server.controller;


import com.cjx.server.pojo.AttendanceSetting;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IAttendanceSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/attendance/setting")
public class AttendanceSettingController {

    @Resource
    private IAttendanceSettingService attendanceSettingService;

    @ApiOperation(value = "获取签到规定")
    @GetMapping("/")
    public List<AttendanceSetting> getAttendanceSetting(){
        return attendanceSettingService.list();
    }

    @ApiOperation(value = "更新签到规定信息")
    @PutMapping("/")
    public RespBean updateAttendanceSetting(@RequestParam Integer id , String value) throws ParseException {
        return attendanceSettingService.updateTime(id, value);
    }
}
