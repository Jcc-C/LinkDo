package com.cjx.server.mapper;

import com.cjx.server.pojo.AttendanceSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CJX
 * @since 2021-12-21
 */
public interface AttendanceSettingMapper extends BaseMapper<AttendanceSetting> {

    /**
     * 查询配置值
     * @param name
     * @return
     */
    String selectSettingValueByName(String name);

    /**
     *更新签到规定时间
     * @param value
     * @return
     */
    int updateTimeById(Integer id , String value);


}
