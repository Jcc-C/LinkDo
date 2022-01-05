package com.cjx.server.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.cjx.server.pojo.Meeting;
import com.cjx.server.mapper.MeetingMapper;
import com.cjx.server.service.IMeetingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.server.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 服务实现类
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {

    @Resource
    private MeetingMapper meetingMapper;

    /*
     * 查询会议(分页)
     * */
    @Override
    public PageUtils searchOfflineMeetingByPage(HashMap param) {
        ArrayList<HashMap> list = meetingMapper.searchOfflineMeetingByPage(param);
        long count = meetingMapper.searchOfflineMeetingCount(param);
        int start = MapUtil.getInt(param, "start");
        int length = MapUtil.getInt(param, "length");
        //将meeting字段转换成JSON数组对象
        for (HashMap map : list) {
            String meeting = (String) map.get("meeting");
            //如果Meeting是有效的字符串，就转换成JSON数组对象
            if (meeting != null && meeting.length() > 0) {
                map.replace("meeting", JSONUtil.parseArray(meeting));
            }
        }
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }


}
