package com.yxf.CoolTalk.dto;

import lombok.Getter;

/**
 * @auther macrozheng
 * @description 消息队列枚举配置
 * @date 2018/9/14
 * @github https://github.com/macrozheng
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("cooltalk.order.direct", "cooltalk.order.cancel", "cooltalk.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("cooltalk.order.direct.ttl", "cooltalk.order.cancel.ttl", "cooltalk.order.cancel.ttl");

    /**
     * 交换机名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
