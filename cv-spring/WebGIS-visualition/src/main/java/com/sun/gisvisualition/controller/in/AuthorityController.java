package com.sun.gisvisualition.controller.in;

import com.sun.gisvisualition.dao.tb_authorityDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    private tb_authorityDao tbAuthorityDao;


    @RequestMapping("getAdmin")
    @ResponseBody
    public List authority() {
        List list = null;
        DataResult dataResult = new DataResult();
        list = tbAuthorityDao.getPk("超级管理员", "管理员");
        System.out.println(list);
        return list;
    }
}
