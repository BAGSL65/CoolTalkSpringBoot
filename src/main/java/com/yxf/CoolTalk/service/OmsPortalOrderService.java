package com.yxf.CoolTalk.service;


import com.yxf.CoolTalk.common.result.CommonResult;
import com.yxf.CoolTalk.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther macrozheng
 * @description 前台订单管理Service
 * @date 2018/8/30
 * @github https://github.com/macrozheng
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
