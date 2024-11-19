package com.brightcomplete.modules.test.seata.order.service;


import com.brightcomplete.modules.test.seata.order.dto.PlaceOrderRequest;

/**
 * @Description: 订单接口
 * @author lmy
 * @date: 2022/01/24
 * @version: V1.0
 */
public interface SeataOrderService {
    /**
     * 下单
     *
     * @param placeOrderRequest 订单请求参数
     */
    void placeOrder(PlaceOrderRequest placeOrderRequest);
}
