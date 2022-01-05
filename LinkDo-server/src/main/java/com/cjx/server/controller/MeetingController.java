package com.cjx.server.controller;


import com.cjx.server.pojo.Admin;
import com.cjx.server.pojo.R;
import com.cjx.server.pojo.RespBean;
import com.cjx.server.pojo.form.SearchOfflineMeetingByPageForm;
import com.cjx.server.service.IMeetingService;
import com.cjx.server.utils.PageUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 会议表 前端控制器
 * </p>
 *
 * @author CJX
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Resource
    private IMeetingService meetingService;


    @ApiOperation(value = "查询线下会议分页数据")
    @PostMapping("/off")
    public R searchOfflineMeetingByPage(@RequestBody SearchOfflineMeetingByPageForm form){
        Admin admin  = new Admin();
        Integer page = form.getPage();
        Integer length = form.getLength();
        Integer start = (page - 1) * length;
        HashMap param = new HashMap(){
            {
                put("date" , form.getDate());
                put("mold" , form.getMold());
                put("userId" , admin.getId());
                put("start" , start);
                put("length" , length);
            }
        };
        PageUtils pageUtils = meetingService.searchOfflineMeetingByPage(param);

        return R.ok().put("page" , pageUtils);
    }

}
