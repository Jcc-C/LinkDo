package com.cjx.server.mapper;

import com.cjx.server.pojo.Meeting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 Mapper 接口
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
public interface MeetingMapper extends BaseMapper<Meeting> {

    //查询会议（分页）
    public ArrayList<HashMap> searchOfflineMeetingByPage(HashMap param);

    //查询会议室
    public long searchOfflineMeetingCount(HashMap param);

}
