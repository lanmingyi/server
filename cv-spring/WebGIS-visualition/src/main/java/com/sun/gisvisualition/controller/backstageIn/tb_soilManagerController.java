package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_soilNutrientsDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_soilNutrients;
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
@RequestMapping("tb_soilManager")
public class tb_soilManagerController {

    @Autowired
    private tb_soilNutrientsDao tbSoilNutrientsDao;

    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_soilNutrients> list = null;
        if (page != null && limit != null) {
            Sort sort = new Sort(Sort.Direction.ASC, "pk");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_soilNutrients> all = tbSoilNutrientsDao.findAll(pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            Sort sort = new Sort(Sort.Direction.ASC, "pk");
            list = tbSoilNutrientsDao.findAll(sort);
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //搜索接口
    @RequestMapping("LikeFind")
    @ResponseBody
    public DataResult LikeFind(String limit, String page, String type) {
        DataResult dataResult = new DataResult();
        if (type == null || type == "") {
            Sort sort = new Sort(Sort.Direction.DESC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_soilNutrients> pages = tbSoilNutrientsDao.findAll(pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_soilNutrients> specification = new Specification<tb_soilNutrients>() {
                @Override
                public Predicate toPredicate(Root<tb_soilNutrients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> name1 = root.get("type");
                    Predicate like = criteriaBuilder.like(name1.as(String.class), "%" + type + "%");
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.DESC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_soilNutrients> all = tbSoilNutrientsDao.findAll(specification, pageable);
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
        tbSoilNutrientsDao.deleteById(pk);
    }

    //更新数据
    @RequestMapping("update")
    @ResponseBody
    public DataResult update(tb_soilNutrients tbUser) {
        DataResult dataResult = new DataResult();
        if (tbSoilNutrientsDao.save(tbUser) != null) {
            dataResult.setMsg("新增成功");
        } else {
            dataResult.setMsg("新增失败");
        }

        return dataResult;
    }

}
