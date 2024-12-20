package com.brightcomplete.modules.test.rabbitmq.listener;

import com.brightcomplete.boot.starter.rabbitmq.core.BaseRabbiMqHandler;
import com.brightcomplete.boot.starter.rabbitmq.listenter.MqListener;
import com.brightcomplete.common.annotation.RabbitComponent;
import com.brightcomplete.common.base.BaseMap;
import com.brightcomplete.modules.test.rabbitmq.constant.CloudConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义接收者（可以定义N个接受者，消息会均匀的发送到N个接收者中）
 * @author lmy
 * @date: 2022/04/21
 */
@Slf4j
@RabbitListener(queues = CloudConstant.MQ_JEECG_PLACE_ORDER_TIME)
@RabbitComponent(value = "helloTimeReceiver")
public class HelloTimeReceiver extends BaseRabbiMqHandler<BaseMap> {

    @RabbitHandler
    public void onMessage(BaseMap baseMap, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        super.onMessage(baseMap, deliveryTag, channel, new MqListener<BaseMap>() {
            @Override
            public void handler(BaseMap map, Channel channel) {
                //业务处理
                String orderId = map.get("orderId").toString();
                log.info("Time Receiver1，orderId : " + orderId);
            }
        });
    }

}