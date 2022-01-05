package com.cjx.server.service.impl;

import com.cjx.server.mapper.MeetingMapper;
import com.cjx.server.pojo.MailLog;
import com.cjx.server.mapper.MailLogMapper;
import com.cjx.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
