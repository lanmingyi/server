package com.sun.gisvisualition.controller.in;

import com.sun.gisvisualition.dao.tb_townshipDao;
import com.sun.gisvisualition.dao.tb_villageDao;
import com.sun.gisvisualition.dao.tb_villagePointDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_township;
import com.sun.gisvisualition.entity.tb_village;
import com.sun.gisvisualition.entity.tb_villagePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("QueryMap")
public class QueryMapController {

    @Autowired
    private tb_villageDao tbVillageDao;

    @Autowired
    private tb_townshipDao tbTownshipDao;

    @Autowired
    private tb_villagePointDao tbVillagePointDao;

    //获取乡镇全部数据
    @RequestMapping("getAllTownship")
    @ResponseBody
    public DataResult getAllTownship(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_township> list = null;
        if (page != null && limit != null) {
            Sort sort = new Sort(Sort.Direction.ASC, "xjqydm");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_township> pages = tbTownshipDao.findAll(pageable);
            list = pages.getContent();
            dataResult.setCount(pages.getTotalElements());
        } else {
            //参数为空时查询全部数据
            Sort sort = new Sort(Sort.Direction.ASC, "xjqydm");
            list = tbTownshipDao.findAll(sort);
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }



    //    获取所有的乡镇pk和name
    @RequestMapping("getTownPkAndName")
    @ResponseBody
    public DataResult GetTownPkAndName() {
        DataResult dataResult = new DataResult();
        Sort sort = new Sort(Sort.Direction.ASC, "pk");
        List list = tbTownshipDao.getTownPkAndName(sort);
        dataResult.setData(list);
        dataResult.setCount((long) list.size());
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //    根据乡镇pk查询
    @RequestMapping("getTownByPk")
    @ResponseBody
    public DataResult getTownByPk(String pk) {
        DataResult dataResult = new DataResult();
        Optional<tb_township> township = tbTownshipDao.findById(pk);
        if (township != null) {
            dataResult.setMsg("查询成功");
            dataResult.setData(township);
            dataResult.setCount((long) 1);
        } else {
            dataResult.setMsg("未查询到数据");
        }
        return dataResult;
    }

    //获取所有村的信息
    @RequestMapping("getAllVillage")
    @ResponseBody
    public DataResult getAllVillage(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_village> list = null;
        if (page != null && limit != null) {
            Sort sort = new Sort(Sort.Direction.ASC, "xjqydm");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_village> pages = tbVillageDao.findAll(pageable);
            list = pages.getContent();
            dataResult.setCount(pages.getTotalElements());
        } else {
            //参数为空时查询全部数据
            Sort sort = new Sort(Sort.Direction.ASC, "cjqydm");
            list = tbVillageDao.findAll(sort);
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //获取村的PK和name
    @RequestMapping("getVillagePkAndName")
    @ResponseBody
    public DataResult getVillagePkAndName() {
        DataResult dataResult = new DataResult();
        Sort sort = new Sort(Sort.Direction.ASC, "pk");
        List list = tbVillageDao.getVillagePkAndName(sort);
        dataResult.setData(list);
        dataResult.setCount((long) list.size());
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //    根据村pk查询村信息
    @RequestMapping("getVillageByPk")
    @ResponseBody
    public DataResult getVillageByPk(String pk) {
        DataResult dataResult = new DataResult();
        Optional<tb_village> tbVillage = tbVillageDao.findById(pk);
        if (tbVillage != null) {
            dataResult.setMsg("查询成功");
            dataResult.setData(tbVillage);
            dataResult.setCount((long) 1);
        } else {
            dataResult.setMsg("未查询到数据");
        }
        return dataResult;
    }


    //获取全部点
    @RequestMapping("getAllVillagePoint")
    @ResponseBody
    public List<tb_villagePoint> getAllPoint() {
        List<tb_villagePoint> allVillagePoint = tbVillagePointDao.getAllVillagePoint();
        return allVillagePoint;
    }
}
