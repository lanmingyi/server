package com.sun.gisvisualition.config.time;

import com.sun.gisvisualition.config.crawler.HttpRequest;
import com.sun.gisvisualition.config.crawler.tb_newsParse;
import com.sun.gisvisualition.dao.crawler.tb_newsDao;
import com.sun.gisvisualition.entity.crawler.tb_news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class SaticScheduleTask {

    @Autowired
    private tb_newsDao tbNewsDao;
    //添加定时任务
    @Scheduled(cron = "0 0 12 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws IOException {
        HttpRequest tb_newsHttpRequest = new HttpRequest();
        String html = tb_newsHttpRequest.getEntityByHttpGetMethod("http://www.agri.cn/V20/ZX/nyyw/index.htm");
        tb_newsParse tbNewsParse = new tb_newsParse();
        List<tb_news> data = tbNewsParse.getData(html);
        tbNewsDao.saveAll(data);
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
