package com.brightcomplete.modules.demo.cloud.service.impl;

import com.brightcomplete.modules.demo.cloud.service.CloudDemoService;
import org.springframework.stereotype.Service;

/**
 * @Description: CloudDemoServiceImpl实现类
 * @author lmy
 */
@Service
public class CloudDemoServiceImpl implements CloudDemoService {
    @Override
    public String getMessage(String name) {
        String resMsg = "Hello，我是brightcomplete-demo服务节点，收到你的消息：【 "+ name +" 】";
        return resMsg;
    }
}
