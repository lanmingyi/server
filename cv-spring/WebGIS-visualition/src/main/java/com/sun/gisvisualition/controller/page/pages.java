package com.sun.gisvisualition.controller.page;

import com.sun.gisvisualition.dao.*;
import com.sun.gisvisualition.dao.crawler.tb_newsDao;
import com.sun.gisvisualition.entity.crawler.tb_news;
import com.sun.gisvisualition.entity.tb_remote;
import com.sun.gisvisualition.entity.tb_soilNutrients;
import com.sun.gisvisualition.entity.tb_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("system")
public class pages {

    @Autowired
    private tb_newsDao tbNewsDao;

    @Autowired
    private tb_townshipDao tbTownshipDao;

    @Autowired
    private tb_villageDao tbVillageDao;

    @Autowired
    private tb_remoteDao tbRemoteDao;

    @Autowired
    private tb_remote_urlDao tbRemoteUrlDao;

    @Autowired
    private tb_soilNutrientsDao tbSoilNutrientsDao;

    @Autowired
    private tb_arcgisServerGPDao tbArcgisServerGPDao;

    //登录页面
    @RequestMapping("login")
    public String login() {
        return "user/login";
    }

    //注册页面
    @RequestMapping("register")
    public String register() {
        return "user/register";
    }

    //主页面
    @RequestMapping("index")
    public String main(HttpSession session, Model model) {
        tb_user user = (tb_user) session.getAttribute("user");
        model.addAttribute("user", user);
        return "main/index";
    }

    //home页面
    @RequestMapping("home")
    public String home(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<tb_news> pages = tbNewsDao.findAll(pageable);
        model.addAttribute("tb_news", pages);
        return "main/home";
    }

    //行政区域显示与查询页面
    @RequestMapping("queryMap")
    public String queryMap(Model model) {
        Sort sortA = new Sort(Sort.Direction.ASC, "pk");
        List town = tbTownshipDao.getTownPkAndName(sortA);
        model.addAttribute("tb_townships", town);
        Sort sortB = new Sort(Sort.Direction.ASC, "pk");
        List village = tbVillageDao.getVillagePkAndName(sortB);
        model.addAttribute("tb_villages", village);
        return "page/queryMap";
    }

    //土壤养分数据可视化页面
    @RequestMapping("soilNutrient")
    public String soilNutrient(Model model) {
        List times = tbSoilNutrientsDao.getTime();
        model.addAttribute("times", times);
        return "page/soilNutrient";
    }

    //土壤墒情数据可视化页面
    @RequestMapping("soilMoisture")
    public String soilMoisture(Model model) {
        List<Map<String, Object>> allGP = tbArcgisServerGPDao.getAllGP("土壤墒情数据可视化");
        model.addAttribute("allGP", allGP);
        return "page/soilMoisture";
    }

    //基本农田植被覆盖度页面
    @RequestMapping("cropEvolution")
    public String cropEvolution(Model model) {
        List allRemote = tbRemoteDao.getAllRemote();
        model.addAttribute("tb_remotes", allRemote);
        return "page/basicFarmlandCoverage";
    }

    //基本农田植被覆盖度页面1
    @RequestMapping("cropEvolution1")
    public String cropEvolution1(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        List allRemote = tbRemoteUrlDao.findAll(sort);
        model.addAttribute("tb_remotes", allRemote);
        return "page/basicFarmlandCoverage1";
    }


    //当涂县农历表页面
    @RequestMapping("lunarCalendar")
    public String lunarCalendar() {
        return "page/lunarCalendar";
    }

    //产量估测页面
    @RequestMapping("productionEstimate")
    public String productionEstimate() {
        return "page/productionEstimate";
    }

    //配方卡页面
    @RequestMapping("recipeCard")
    public String recipeCard() {
        return "page/recipeCard";
    }

    //配方地图页面
    @RequestMapping("recipeMap")
    public String recipeMap(Model model) {
        List<Map<String, Object>> allGP = tbArcgisServerGPDao.getAllGP("配方地图");
        model.addAttribute("allGP", allGP);
        return "page/recipeMap";
    }

    //土壤墒情预警
    @RequestMapping("soilMoistureWarning")
    public String soilMoistureWarning() {
        return "page/soilMoistureWarning";
    }

    //选址规划支持分析
    @RequestMapping("locationPlanning")
    public String locationPlanning(Model model) {
        List<Map<String, Object>> allGP = tbArcgisServerGPDao.getAllGP("选址规划支持分析");
        model.addAttribute("allGP", allGP);
        return "page/locationPlanning";
    }


    //退出
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        //清除登录信息
        session.invalidate();
        return "forward:/system/login";
    }
}
