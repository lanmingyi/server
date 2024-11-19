package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_meteorologicalOneDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_meteorologicalOne;
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
import java.util.List;

@Controller
@RequestMapping("tb_meteorological")
public class tb_meteorologicalController {

    @Autowired
    private tb_meteorologicalOneDao tbMeteorologicalDao;

    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_meteorologicalOne> list = null;
        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit));
            Page<tb_meteorologicalOne> all = tbMeteorologicalDao.findAll(pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            list = tbMeteorologicalDao.findAll();
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //搜索接口
    @RequestMapping("LikeFind")
    @ResponseBody
    public DataResult LikeFind(String limit, String page, String date) {
        DataResult dataResult = new DataResult();
        if (date == null || date == "") {
            Sort sort = new Sort(Sort.Direction.DESC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_meteorologicalOne> pages = tbMeteorologicalDao.findAll(pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_meteorologicalOne> specification = new Specification<tb_meteorologicalOne>() {
                @Override
                public Predicate toPredicate(Root<tb_meteorologicalOne> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> name = root.get("date");
                    Predicate like = criteriaBuilder.like(name.as(String.class), date + "%");
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.DESC, "pk");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_meteorologicalOne> all = tbMeteorologicalDao.findAll(specification, pageable);
            dataResult.setData(all.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(all.getTotalElements());
        }
        return dataResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public void delete(String pk) {
        tbMeteorologicalDao.deleteById(pk);
    }

    @RequestMapping("update")
    @ResponseBody
    public void update(tb_meteorologicalOne tbMeteorological) {
        tbMeteorologicalDao.save(tbMeteorological);
    }
}
