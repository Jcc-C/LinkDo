package com.cjx.service.impl;

import com.cjx.pojo.Meeting;
import com.cjx.mapper.MeetingMapper;
import com.cjx.service.IMeetingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
