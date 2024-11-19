package com.brightcomplete.modules.test.rabbitmq.listener;//package org.jeecg.modules.cloud.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.boot.starter.rabbitmq.core.BaseRabbiMqHandler;
import com.brightcomplete.boot.starter.rabbitmq.listenter.MqListener;
import com.brightcomplete.common.annotation.RabbitComponent;
import com.brightcomplete.common.base.BaseMap;
import com.brightcomplete.modules.test.rabbitmq.constant.CloudConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 定义接收者（可以定义N个接受者，消息会均匀的发送到N个接收者中）
 *
 * RabbitMq接受者2
 * （@RabbitListener声明类上，一个类只能监听一个队列）
 * @author lmy
 * @date: 2022/04/21
 */
@Slf4j
@RabbitListener(queues = CloudConstant.MQ_JEECG_PLACE_ORDER)
@RabbitComponent(value = "helloReceiver2")
public class HelloReceiver2 extends BaseRabbiMqHandler<BaseMap> {

    @RabbitHandler
    public void onMessage(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
            @Override
            public void handler(BaseMap map, Channel channel) {
                //业务处理
                String orderId = map.get("orderId").toString();
                log.info("【我是处理人2】 MQ Receiver2，orderId : " + orderId);
            }
        });
    }

}