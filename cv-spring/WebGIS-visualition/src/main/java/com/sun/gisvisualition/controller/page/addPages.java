package com.sun.gisvisualition.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("addPage")
public class addPages {

    //新增用户页面
    @RequestMapping("addUser")
    public String addUser() {
        return "adds/user-add";
    }

    //新增gp服务页面
    @RequestMapping("addGp")
    public String addGP() {
        return "adds/GP-add";
    }

    //新增遥感数据页面
    @RequestMapping("addRemote")
    public String addRemote() {
        return "adds/remote-add";
    }

    //新增土壤养分服务页面
    @RequestMapping("addSoil")
    public String addSoil(){
        return "adds/soil-add";
    }

}
