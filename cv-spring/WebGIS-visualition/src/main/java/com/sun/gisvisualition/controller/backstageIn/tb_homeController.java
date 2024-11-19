package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("tb_home")
public class tb_homeController {

    @Autowired
    tb_townshipDao tbTownshipDao;

    @Autowired
    tb_villageDao tbVillageDao;

    @Autowired
    tb_remote_urlDao tbRemoteUrlDao;

    @Autowired
    tb_userDao tbUserDao;


    @RequestMapping("countTownship")
    @ResponseBody
    public long countTownship() {
        return tbTownshipDao.count();
    }

    @RequestMapping("countVillage")
    @ResponseBody
    public long countVillage() {
        return tbVillageDao.count();
    }

    @RequestMapping("countRemote")
    @ResponseBody
    public long countRemote() {
        return tbRemoteUrlDao.count();
    }

    @RequestMapping("countUser")
    @ResponseBody
    public long countUser() {
        return tbUserDao.count();
    }

}
