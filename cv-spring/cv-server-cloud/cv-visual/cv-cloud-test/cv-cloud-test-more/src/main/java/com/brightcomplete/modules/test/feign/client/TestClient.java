package com.brightcomplete.modules.test.feign.client;

import com.brightcomplete.common.constant.ServiceNameConstants;
import com.brightcomplete.modules.test.feign.factory.TestClientFactory;
import com.brightcomplete.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 常规feign接口定义
 * @author lmy
 * @date: 2022/04/21
 */
@FeignClient(value = ServiceNameConstants.SERVICE_DEMO, configuration = FeignConfig.class,fallbackFactory = TestClientFactory.class)
@Component
public interface TestClient {

    /**
     * feign测试方法
     * @param name
     * @return
     */
    @GetMapping(value = "/test/getMessage")
    String getMessage(@RequestParam(value = "name",required = false) String name);
}
