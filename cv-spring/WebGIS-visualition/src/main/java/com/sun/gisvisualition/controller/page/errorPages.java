package com.sun.gisvisualition.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class errorPages {

    //没有权限
    @RequestMapping("403")
    public String error403(){
        return "error/403";
    }

    //未找到页面
    @RequestMapping("404")
    public String error404(){
        return "error/404";
    }

    //服务器错误
    @RequestMapping("500")
    public String error500(){
        return "error/500";
    }
}
