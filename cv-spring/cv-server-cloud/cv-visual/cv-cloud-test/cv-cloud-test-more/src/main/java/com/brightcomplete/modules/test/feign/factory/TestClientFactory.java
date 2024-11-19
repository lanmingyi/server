package com.brightcomplete.modules.test.feign.factory;




import org.springframework.cloud.openfeign.FallbackFactory;
import com.brightcomplete.modules.test.feign.client.TestClient;
import com.brightcomplete.modules.test.feign.fallback.TestFallback;
import org.springframework.stereotype.Component;

/**
 * @author lmy
 */
@Component
public class TestClientFactory implements FallbackFactory<TestClient> {

    @Override
    public TestClient create(Throwable throwable) {
        TestFallback fallback = new TestFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
