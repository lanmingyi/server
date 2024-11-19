package com.sun.gisvisualition;

import com.sun.gisvisualition.config.crawler.HttpRequest;
import com.sun.gisvisualition.config.crawler.tb_newsParse;
import com.sun.gisvisualition.dao.crawler.tb_newsDao;
import com.sun.gisvisualition.entity.crawler.tb_news;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GisvisualitionApplicationTests {

    @Autowired
    private tb_newsDao tbNewsDao;

    String url = "http://www.agri.cn/V20/ZX/nyyw/index";
    int a = 34;

    @Test
    public void contextLoads() throws IOException {
//        HttpRequest tb_newsHttpRequest = new HttpRequest();
//
//        for (int i = 0; i < 34; i++) {
//            String html;
//            if (i == 0) {
//                html = tb_newsHttpRequest.getEntityByHttpGetMethod(url + ".htm");
//            } else {
//                html = tb_newsHttpRequest.getEntityByHttpGetMethod(url + "_" + i + ".htm");
//            }
//            tb_newsParse tbNewsParse = new tb_newsParse();
//            List<tb_news> data = tbNewsParse.getData(html);
//            tbNewsDao.saveAll(data);
//        }
    }

}
