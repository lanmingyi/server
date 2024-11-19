//package com.brightcomplete.modules.test.feign.client;
//
//
//import com.brightcomplete.common.api.vo.Result;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * 动态feign接口定义
// */
//public interface TestClientDyn {
//
//    @GetMapping(value = "/test/getMessage")
//    Result<String> getMessage(@RequestParam(value = "name",required = false) String name);
//}
