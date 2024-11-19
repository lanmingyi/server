package com.sun.gisvisualition.config.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerInterceptor implements WebMvcConfigurer {
    @Autowired
    private IsLoginInterceptor isLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册登录验证拦截器
        registry.addInterceptor(isLoginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/", "/system/login", "/system/register", "/user/isLogin", "/user/register", "/static/**");
    }
}
