package com.cjx.server.pojo;

public class MailConstants {

    //消息投递中
    public static final Integer DELIVERING = 0;

    //消息投递成功
    public static final Integer SUCCESS = 1;

    //消息投递失败
    public static final Integer FAILURE = 2;

    //最大重试次数
    public static final Integer MAX_TRY_COUNT = 3;

    //消息超时时间
    public static final Integer MSG_TIMEOUT = 1;

    //队列
    public static final String MAIL_QUEUE_NAME = "Mail.queue";

    //交换机
    public static final String MAIL_EXCHANGE_NAME = "Mail.exchange";

    //路由器
    public static final String MAIL_ROUTING_KEY_NAME = "Mail.routing.key";

}
