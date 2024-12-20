# cv-starter
当前最新版本： 1.0.0（发布日期：2022-08-06）

### 介绍
cv-spring的starter启动模块独立出来，简化项目，便于维护。

### 软件架构
  spring-cloud：2021.0.3
  spring-cloud-alibaba：2021.0.1.0



### cv-starter项目说明

``` 
├── cv-starter              -- starter父模块
    ├── cv-common              -- 底层共通类（单体和微服务公用）
    ├── cv-starter-cloud       -- 微服务启动starter
    ├── cv-starter-job           -- xxl-job定时任务starter
    ├── cv-starter-lock          -- 分布式锁starter
    ├── cv-starter-rabbitmq       -- 消息中间件starter
    ├── cv-starter-seata           --分布式事务starter
    ├── cv-starter-shardingsphere  -- 分库分表starter
    ├── cv-starter-mongon          -- mongostarter
```

### 技术支持

使用中遇到问题或BUG可以在 [cv-spring主项目上提Issues](https://github.com/lanmingyi/cv-spring/issues/new)

官方支持： http://brightcomplete.com/doc/help


### 开发文档

- http://doc.brightcomplete.com