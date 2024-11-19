package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_remoteDao;
import com.sun.gisvisualition.dao.tb_remote_urlDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_remote;
import com.sun.gisvisualition.entity.tb_remote_url;
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
@RequestMapping("tb_remote")
public class tb_remoteController {

    @Autowired
    private tb_remote_urlDao tbRemoteDao;

    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        List<tb_remote_url> list = null;
        if (page != null && limit != null) {
            Sort sort = new Sort(Sort.Direction.DESC, "date");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_remote_url> all = tbRemoteDao.findAll(pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            Sort sort = new Sort(Sort.Direction.DESC, "date");//使用爬取时间排序
            list = tbRemoteDao.findAll(sort);
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
            Sort sort = new Sort(Sort.Direction.DESC, "date");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_remote_url> pages = tbRemoteDao.findAll(pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_remote_url> specification = new Specification<tb_remote_url>() {
                @Override
                public Predicate toPredicate(Root<tb_remote_url> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> name = root.get("date");
                    Predicate like = criteriaBuilder.like(name.as(String.class), date + "%");
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.DESC, "date");//使用爬取时间排序
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_remote_url> all = tbRemoteDao.findAll(specification, pageable);
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
        tbRemoteDao.deleteById(pk);
    }

    //更新数据
    @RequestMapping("update")
    @ResponseBody
    public void update(tb_remote_url tbRemote) {
        tbRemoteDao.save(tbRemote);
    }
}
