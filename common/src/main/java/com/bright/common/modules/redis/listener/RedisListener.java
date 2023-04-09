package com.bright.common.modules.redis.listener;

import com.bright.common.base.BaseMap;

/**
 * 自定义消息监听
 */
public interface RedisListener {
    /**
     * 接受消息
     *
     * @param message
     */
    void onMessage(BaseMap message);

}
