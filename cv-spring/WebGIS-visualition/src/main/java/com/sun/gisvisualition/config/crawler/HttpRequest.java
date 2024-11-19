package com.sun.gisvisualition.config.crawler;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequest {

    //获取网页内容
    public String getEntityByHttpGetMethod(String url) throws IOException {
        HttpClient httpClient = HttpClients.custom().build(); //初始化httpclient
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = null;//请求响应
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        String html = EntityUtils.toString(httpEntity, "utf-8");
        //System.out.println(html);
        return html;
    }


}
