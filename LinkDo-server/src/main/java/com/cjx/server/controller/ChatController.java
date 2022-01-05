package com.cjx.server.controller;

import com.cjx.server.pojo.Admin;
import com.cjx.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 在线聊天控制器
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keyWords){
        return adminService.getAllAdmins(keyWords);
    }
}