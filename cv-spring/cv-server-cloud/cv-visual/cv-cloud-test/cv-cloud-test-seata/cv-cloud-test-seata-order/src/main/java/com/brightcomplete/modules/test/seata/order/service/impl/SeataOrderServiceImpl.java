package com.brightcomplete.modules.test.seata.order.service.impl;
import com.baomidou.dynamic.datasource.annotation.DS;

import com.brightcomplete.modules.test.seata.order.dto.PlaceOrderRequest;
import com.brightcomplete.modules.test.seata.order.entity.SeataOrder;
import com.brightcomplete.modules.test.seata.order.enums.OrderStatus;
import com.brightcomplete.modules.test.seata.order.feign.AccountClient;
import com.brightcomplete.modules.test.seata.order.feign.ProductClient;
import com.brightcomplete.modules.test.seata.order.mapper.SeataOrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.modules.test.seata.order.service.SeataOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description: 订单服务类
 * @author lmy
 * @date: 2022/01/24
 * @version: V1.0
 */
@Slf4j
@Service
public class SeataOrderServiceImpl implements SeataOrderService {

    @Resource
    private SeataOrderMapper orderMapper;
    @Resource
    private AccountClient accountClient;
    @Resource
    private ProductClient productClient;

    @DS("order")
    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public void placeOrder(PlaceOrderRequest request) {
        log.info("=============ORDER START=================");
        Long userId = request.getUserId();
        Long productId = request.getProductId();
        Integer count = request.getCount();
        log.info("收到下单请求,用户:{}, 商品:{},数量:{}", userId, productId, count);


        SeataOrder order = SeataOrder.builder()
                .userId(userId)
                .productId(productId)
                .status(OrderStatus.INIT)
                .count(count)
                .build();

        orderMapper.insert(order);
        log.info("订单一阶段生成，等待扣库存付款中");
        // 扣减库存并计算总价
        BigDecimal amount = productClient.reduceStock(productId, count);
        // 扣减余额
        accountClient.reduceBalance(userId, amount);

        order.setStatus(OrderStatus.SUCCESS);
        order.setTotalPrice(amount);
        orderMapper.updateById(order);
        log.info("订单已成功下单");
        log.info("=============ORDER END=================");
    }
}