package com.cjx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.server.mapper.AttendanceSettingMapper;
import com.cjx.server.pojo.AttendanceSetting;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.service.IAttendanceSettingService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
@Service
public class AttendanceSettingServiceImpl extends ServiceImpl<AttendanceSettingMapper, AttendanceSetting> implements IAttendanceSettingService {

    @Resource
    private AttendanceSettingMapper attendanceSettingMapper;

    /**
     * 更新签到规定时间
     * @param id
     * @param value
     * @return
     */
    @Override
    public RespBean updateTime(Integer id, String value) throws ParseException {
        AttendanceSetting attendanceSetting = new AttendanceSetting();
        //根据UpdateWrapper筛选条件
       UpdateWrapper updateWrapper = new UpdateWrapper<>();
       updateWrapper.eq("id" , id);
       //需要更新的字段
//        Date parse = new SimpleDateFormat("HH:mm:ss").parse(value);
        updateWrapper.set("value" , value);
        int result = attendanceSettingMapper.update(attendanceSetting, updateWrapper);
        if (1 == result){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
