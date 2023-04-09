package com.bright.common.modules.redis.receiver;


import cn.hutool.core.util.ObjectUtil;
import com.bright.common.base.BaseMap;
import com.bright.common.constant.GlobalConstants;
import com.bright.common.modules.redis.listener.RedisListener;
import com.bright.common.util.SpringContextHolder;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class RedisReceiver {


    /**
     * 接受消息并调用业务逻辑处理器
     *
     * @param params
     */
    public void onMessage(BaseMap params) {
        Object handlerName = params.get(GlobalConstants.HANDLER_NAME);
        RedisListener messageListener = SpringContextHolder.getHandler(handlerName.toString(), RedisListener.class);
        if (ObjectUtil.isNotEmpty(messageListener)) {
            messageListener.onMessage(params);
        }
    }

}
