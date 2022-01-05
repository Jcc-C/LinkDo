package com.cjx.server.service;

import com.cjx.server.pojo.AttendanceSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.server.pojo.RespBean;

import java.text.ParseException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
public interface IAttendanceSettingService extends IService<AttendanceSetting> {

    /**
     * 更新签到规定
     * @param id
     * @param value
     * @return
     */
    RespBean updateTime(Integer id, String value) throws ParseException;
}
