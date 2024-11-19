package com.sun.gisvisualition.config.crawler;

import com.sun.gisvisualition.entity.crawler.tb_news;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class tb_newsParse {

    public List<tb_news> getData(String html) {
        //获取的数据，存放在集合中
        List<tb_news> data = new ArrayList<tb_news>();
        //采用Jsoup解析
        Document document = Jsoup.parse(html);
        Elements table = document.select("td[class=bk_7]");
        Elements elements = table.select("table[width=659]");

        String url = "http://www.agri.cn/V20/ZX/nyyw";
        for (Element element : elements) {
            Elements td_a = element.select("td[class=bj_3-2]");
            String href_a = td_a.select("a[class=link03]").attr("href");
            String href = url + href_a.substring(href_a.indexOf("/"));
            //System.out.println(href);
            String title_a = td_a.select("a[class=link03]").select("script").toString();
            String title_b = title_a.substring(title_a.indexOf(">") + 1, title_a.indexOf(")"));
            String title_c = title_b.substring(title_b.indexOf(">") + 1);
            String title = title_c.substring(0, title_c.indexOf("<"));
            //System.out.println(title);
            Elements td_b = element.select("td[class=hui_14]");
            String time_a = td_b.text();
            String time = time_a.substring(time_a.indexOf("(") + 1, time_a.indexOf(")"));
            //System.out.println(time);

            String crawler_time = TimeUtils.GetNowDate("yyyy-MM-dd HH:mm:ss");
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            tb_news tbNews = new tb_news();
            //对象赋值
            tbNews.setUrl(href);
            tbNews.setTitle(title);
            tbNews.setDate(time);
            tbNews.setCrawlerTime(crawler_time);
            //将每一个对象的值，保存到List集合中
            data.add(tbNews);
        }
        //System.out.println(data);
        return data;
    }
}
