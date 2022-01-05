package com.cjx.server.service;

import com.cjx.server.pojo.Meeting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.server.pojo.RespPageBean;
import com.cjx.server.utils.PageUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 服务类
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
public interface IMeetingService extends IService<Meeting> {

    public PageUtils searchOfflineMeetingByPage(HashMap params);
}
