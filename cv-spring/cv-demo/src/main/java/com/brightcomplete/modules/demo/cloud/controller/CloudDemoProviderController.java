package com.brightcomplete.modules.demo.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.modules.demo.cloud.service.CloudDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务端提供方——feign接口
 * 【提供给system-start调用测试，看feign是否畅通】
 * @author lmy
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class CloudDemoProviderController {

    @Resource
    private CloudDemoService cloudDemoService;

    @GetMapping("/getMessage")
    public String getMessage(@RequestParam String name) {
        String msg = cloudDemoService.getMessage(name);
        log.info(" 微服务被调用：{} ",msg);
        return msg;
    }

}
