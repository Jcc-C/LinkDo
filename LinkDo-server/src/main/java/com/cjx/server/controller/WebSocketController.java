package com.cjx.server.controller;

import com.cjx.server.pojo.Admin;
import com.cjx.server.pojo.ChatMsg;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * WebSocket控制类
 */

@Controller
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handlerMsg(Authentication authentication , ChatMsg chatMsg){

        Admin admin = (Admin) authentication.getPrincipal();
        chatMsg.setFrom(admin.getUsername());
        chatMsg.setFromNickName(admin.getUsername());
        chatMsg.setDate(LocalDateTime.now());

        //发送消息
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo() , "/queue/chat" , chatMsg);
    }
}
