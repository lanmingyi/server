package com.sun.gisvisualition.controller.page;

import com.sun.gisvisualition.dao.tb_authorityDao;
import com.sun.gisvisualition.entity.tb_authority;
import com.sun.gisvisualition.entity.tb_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("backstage")
public class backstagePages {

    @Autowired
    private tb_authorityDao tbAuthorityDao;

    @RequestMapping("index")
    public String mian(HttpSession session, Model model) {
        String url = "backstage/main/index";
        tb_user user = (tb_user) session.getAttribute("user");
        model.addAttribute("user", user);
        List<tb_authority> list = null;
        list = tbAuthorityDao.getPk("超级管理员", "管理员");
        String a = user.getAuthority();
        String b = list.get(0).getPk();
        String c = list.get(1).getPk();
        if (!a.equals(b) && !a.equals(c)) {
            return "forward:/error/403";
        }
        return "backstage/main/index";
    }

    //主页面
    @RequestMapping("home")
    public String home() {
        return "backstage/page/home";
    }

    //土壤养分管理页面
    @RequestMapping("tb_soil")
    public String tb_soil() {
        return "backstage/page/tb_soil";
    }

    //土壤墒情管理页面
    @RequestMapping("tb_meteorological")
    public String tb_meteorological() {
        return "backstage/page/tb_meteorological";
    }

    //遥感数据管理页面
    @RequestMapping("tb_remote")
    public String tb_remote() {
        return "backstage/page/tb_remote";
    }

    //乡镇信息管理页面
    @RequestMapping("tb_township")
    public String tb_township() {
        return "backstage/page/tb_township";
    }

    //村信息管理页面
    @RequestMapping("tb_village")
    public String tb_village() {
        return "backstage/page/tb_village";
    }

    //农业要闻
    @RequestMapping("tb_news")
    public String tb_news() {
        return "backstage/page/tb_news";
    }

    //gp服务管理页面
    @RequestMapping("tb_arcgisServerGp")
    public String tb_arcgisServer() {
        return "backstage/page/tb_arcgisServerGp";
    }

    //土壤养分服务管理页面
    @RequestMapping("tb_soilManager")
    public String tb_soilManager(){
        return "backstage/page/tb_soilManager";
    }

    //用户管理
    @RequestMapping("tb_users")
    public String tb_users(HttpSession session, Model model) {
        tb_user user = (tb_user) session.getAttribute("user");
        List<tb_authority> list = null;
        list = tbAuthorityDao.getPk("超级管理员", "管理员");
        String a = user.getAuthority();
        String b = list.get(0).getPk();
        String c = list.get(1).getPk();
        if (!a.equals(b)) {
            return "forward:/error/403";
        }
        return "backstage/page/tb_users";
    }
}
