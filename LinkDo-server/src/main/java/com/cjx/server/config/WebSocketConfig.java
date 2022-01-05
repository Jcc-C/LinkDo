package com.cjx.server.config;

import com.cjx.server.config.security.component.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;

/**
 * websocket配置类
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserDetailsService userDetailsService;


    /**
     * 添加断点，这样就可以在网页通过websocket进行连接
     * 配置前端的服务器地址，并且指定是否使用socketJS
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        /**
         * 1. 将ws/ep路径注册为stomp端点，用户连接这个端点后就可以进行通讯，支持socketJS
         * 2. setAllowedOriginPatterns("*") 允许跨域
         * 3. withSockJS() 支持socketJS访问
         */
        registry.addEndpoint("ws/ep").setAllowedOriginPatterns("*").withSockJS();

    }

    /**
     * 输入通道参数配置
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否连接，如果是，需要获取token，并且设置用户对象；
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)){
                        //获取完整令牌
                        String  authToken = token.substring(tokenHead.length());
                        String userNameFromToken = jwtTokenUtil.getUserNameFromToken(authToken);
                        //判断token中是否存在用户名
                        if (!StringUtils.isEmpty(userNameFromToken)){
                            //登陆
                            UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
                            //判断userDetails是否有效
                            if (jwtTokenUtil.validateToken(authToken , userDetails)){
                                //重新设置用户对象
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                //spring全局对象设置
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，可以配置多个，配置代理目的地前缀为/queue，可以在配置域上向客户端推送信息
        registry.enableSimpleBroker("/queue");
    }
}
