package com.brightcomplete.handler;

import com.brightcomplete.loader.DynamicRouteLoader;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.base.BaseMap;
import com.brightcomplete.common.constant.GlobalConstants;
import com.brightcomplete.common.modules.redis.listener.RedisListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 路由刷新监听（实现方式：redis监听handler）
 * @author lmy
 * @date: 2022/4/21 10:55
 */
@Slf4j
@Component(GlobalConstants.LODER_ROUDER_HANDLER)
public class LoderRouderHandler implements RedisListener {

    @Resource
    private DynamicRouteLoader dynamicRouteLoader;


    @Override
    public void onMessage(BaseMap message) {
        dynamicRouteLoader.refresh(message);
    }

}