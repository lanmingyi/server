package com.sun.gisvisualition.controller.backstageIn;

import com.sun.gisvisualition.dao.tb_authorityDao;
import com.sun.gisvisualition.dao.tb_userDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_authority;
import com.sun.gisvisualition.entity.tb_user;
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
@RequestMapping("tb_users")
public class tb_usersController {

    @Autowired
    private tb_userDao tbUserDao;

    @Autowired
    private tb_authorityDao tbAuthorityDao;


    //获取全部数据或分页获取
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult getAll(String limit, String page) {
        DataResult dataResult = new DataResult();
        tb_authority byAuthority = tbAuthorityDao.findByAuthority("超级管理员");
        List<tb_user> list = null;
        if (page != null && limit != null) {
            //该条件是不查询超管
            Specification<tb_user> specification = new Specification<tb_user>() {
                @Override
                public Predicate toPredicate(Root<tb_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> authority = root.get("authority");
                    Predicate like = criteriaBuilder.notEqual(authority, byAuthority.getPk());
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.ASC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_user> all = tbUserDao.findAll(specification, pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        } else {
            //该条件是不查询超管
            Specification<tb_user> specification = new Specification<tb_user>() {
                @Override
                public Predicate toPredicate(Root<tb_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> authority = root.get("authority");
                    Predicate like = criteriaBuilder.notEqual(authority, byAuthority.getPk());
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.ASC, "pk");
            Pageable pageable = PageRequest.of(0, 10, sort);
            Page<tb_user> all = tbUserDao.findAll(specification, pageable);
            list = all.getContent();
            dataResult.setCount(all.getTotalElements());
        }
        dataResult.setData(list);
        dataResult.setMsg("查询成功");
        return dataResult;
    }

    //搜索接口
    @RequestMapping("LikeFind")
    @ResponseBody
    public DataResult LikeFind(String limit, String page, String phone) {
        DataResult dataResult = new DataResult();
        tb_authority byAuthority = tbAuthorityDao.findByAuthority("超级管理员");
        if (phone == null || phone == "") {
            //该条件是不查询超管
            Specification<tb_user> specification = new Specification<tb_user>() {
                @Override
                public Predicate toPredicate(Root<tb_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> authority = root.get("authority");
                    Predicate like = criteriaBuilder.notEqual(authority, byAuthority.getPk());
                    return like;
                }
            };
            Sort sort = new Sort(Sort.Direction.ASC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_user> pages = tbUserDao.findAll(specification, pageable);
            dataResult.setData(pages.getContent());
            dataResult.setMsg("查询成功");
            dataResult.setCount(pages.getTotalElements());
        } else {
            Specification<tb_user> specification = new Specification<tb_user>() {
                @Override
                public Predicate toPredicate(Root<tb_user> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<Object> phone1 = root.get("phone");
                    Path<Object> authority = root.get("authority");
                    Predicate predicate = criteriaBuilder.notEqual(authority, byAuthority.getPk());
                    Predicate like = criteriaBuilder.like(phone1.as(String.class), "%" + phone + "%");
                    Predicate and = criteriaBuilder.and(predicate, like);
                    return and;
                }
            };
            Sort sort = new Sort(Sort.Direction.ASC, "pk");
            Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), sort);
            Page<tb_user> all = tbUserDao.findAll(specification, pageable);
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
        tbUserDao.deleteById(pk);
    }

    //新增数据
    @RequestMapping("add")
    @ResponseBody
    public DataResult add(tb_user tbUser) {
        DataResult dataResult = new DataResult();
        if (tbUserDao.countByUsername(tbUser.getUsername()) != 0) {
            dataResult.setMsg("用户已存在");
            dataResult.setCode(1);
            return dataResult;
        }
        if (tbUserDao.countByPhone(tbUser.getPhone()) != 0) {
            dataResult.setMsg("电话号码已经注册");
            dataResult.setCode(1);
            return dataResult;
        }
        String authority = tbUser.getAuthority();
        tb_authority byAuthority = tbAuthorityDao.findByAuthority(authority);//获取权限pk
        tbUser.setAuthority(byAuthority.getPk());
        if (tbUserDao.save(tbUser) != null) {
            dataResult.setMsg("新增成功");
        } else {
            dataResult.setMsg("新增失败");
        }

        return dataResult;
    }

    //更新数据
    @RequestMapping("update")
    @ResponseBody
    public DataResult update(tb_user tbUser) {
        DataResult dataResult = new DataResult();
        if (tbUserDao.save(tbUser) != null) {
            dataResult.setMsg("修改成功");
        } else {
            dataResult.setMsg("修改失败");
        }

        return dataResult;
    }
}
