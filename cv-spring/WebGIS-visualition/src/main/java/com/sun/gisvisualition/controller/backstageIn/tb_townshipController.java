package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_townshipDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_township;
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
@RequestMapping("tb_township")
public class tb_townshipController {

    @Autowired
    private tb_townshipDao tbTownshipDao;

    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_township> list = null;
        if (page != null && limit != null) {
            Sort sort = new Sort(Sort.Direction.DESC, "xjqydm");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_township> all = tbTownshipDao.findAll(pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            Sort sort = new Sort(Sort.Direction.DESC, "xjqydm");//使用爬取时间排序
            list = tbTownshipDao.findAll(sort);
            dataResult.setCount((long) list.size());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //搜索接口
    @RequestMapping("LikeFind")
    @ResponseBody
    public DataResult LikeFind(String limit, String page, String name) {
        DataResult dataResult = new DataResult();
        if (name == null || name == "") {
            Sort sort = new Sort(Sort.Direction.DESC, "xjqydm");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_township> pages = tbTownshipDao.findAll(pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_township> specification = new Specification<tb_township>() {
                @Override
                public Predicate toPredicate(Root<tb_township> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> name1 = root.get("name");
                    Predicate like = criteriaBuilder.like(name1.as(String.class), "%" + name + "%");
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.DESC, "xjqydm");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_township> all = tbTownshipDao.findAll(specification, pageable);
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
        tbTownshipDao.deleteById(pk);
    }

    //更新数据
    @RequestMapping("update")
    @ResponseBody
    public void update(tb_township tbRemote) {
        tbTownshipDao.save(tbRemote);
    }
}
