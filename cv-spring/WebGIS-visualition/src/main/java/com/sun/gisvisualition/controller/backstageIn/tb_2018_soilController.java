package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_2018_soilDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_2018_soil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import javax.swing.text.html.HTMLDocument;
import java.util.List;

@RequestMapping("tb_2018_soil")
@Controller
public class tb_2018_soilController {

    @Autowired
    private tb_2018_soilDao tb2018SoilDao;

    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_2018_soil> list = null;
        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit));
            Page<tb_2018_soil> all = tb2018SoilDao.findAll(pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            list = tb2018SoilDao.findAll();
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //搜索接口
    @RequestMapping("LikeFind")
    @ResponseBody
    public DataResult LikeFind(String limit, String page, String latitude) {
        DataResult dataResult = new DataResult();
        if (latitude == null || latitude == "") {
            Sort sort = new Sort(Sort.Direction.DESC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_2018_soil> pages = tb2018SoilDao.findAll(pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_2018_soil> specification = new Specification<tb_2018_soil>() {
                @Override
                public Predicate toPredicate(Root<tb_2018_soil> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> name = root.get("latitude");
                    Predicate like = criteriaBuilder.like(name.as(String.class), latitude + "%");
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.DESC, "pk");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_2018_soil> all = tb2018SoilDao.findAll(specification, pageable);
            dataResult.setData(all.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(all.getTotalElements());
        }
        return dataResult;
    }

    //根据pk删除数据
    @RequestMapping("delete")
    @ResponseBody
    public void delete(String pk) {
        tb2018SoilDao.deleteById(pk);
    }

    //更新数据
    @RequestMapping("update")
    @ResponseBody
    public void update(tb_2018_soil tb2018Soil) {
        tb2018SoilDao.save(tb2018Soil);
    }


}
