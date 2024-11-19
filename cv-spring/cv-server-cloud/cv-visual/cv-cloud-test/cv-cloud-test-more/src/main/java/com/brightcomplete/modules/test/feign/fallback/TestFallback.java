package com.brightcomplete.modules.test.feign.fallback;

import com.brightcomplete.modules.test.feign.client.TestClient;
import lombok.Setter;


/**
* 接口fallback实现
* 
* @author: lmy
* @date: 2022/4/11 19:41
*/
public class TestFallback implements TestClient {

    @Setter
    private Throwable cause;


    @Override
    public String getMessage(String name) {
        return "访问超时, 自定义FallbackFactory";
    }
}
