package com.brightcomplete.common.modules.redis.receiver;


import cn.hutool.core.util.ObjectUtil;
import com.brightcomplete.common.base.BaseMap;
import com.brightcomplete.common.constant.GlobalConstants;
import com.brightcomplete.common.modules.redis.listener.RedisListener;
import com.brightcomplete.common.util.SpringContextHolder;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author lmy
 */
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
