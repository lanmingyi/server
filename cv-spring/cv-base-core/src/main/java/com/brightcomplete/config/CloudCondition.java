package com.brightcomplete.config;

import com.brightcomplete.common.constant.CommonConstant;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 微服务环境加载条件
 * @author lmy
 */
public class CloudCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Object object = context.getEnvironment().getProperty(CommonConstant.CLOUD_SERVER_KEY);
        //如果没有服务注册发现的配置 说明是单体应用
        if(object==null){
            return false;
        }
        return true;
    }
}