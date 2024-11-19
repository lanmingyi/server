package com.brightcomplete.common.modules.redis.listener;

import com.brightcomplete.common.base.BaseMap;

/**
 * @Description: 自定义消息监听
 * @author: lmy
 * @date: 2020/01/01 16:02
 */
public interface RedisListener {
    /**
     * 接受消息
     *
     * @param message
     */
    void onMessage(BaseMap message);

}
