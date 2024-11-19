package com.sun.gisvisualition.controller.in;

import com.sun.gisvisualition.config.exception.DataResultException;
import com.sun.gisvisualition.dao.tb_userDao;
import com.sun.gisvisualition.dao.tb_user_infoDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_user;
import com.sun.gisvisualition.entity.tb_user_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("user")
public class userController {

    @Autowired
    private tb_userDao tbUserDao;

    @Autowired
    private tb_user_infoDao tbUserInfoDao;


    @RequestMapping("isLogin")
    @ResponseBody
    public DataResult isLogin(String username, String password, HttpSession session) {
        DataResult dataResult = new DataResult();
        tb_user tbUser = new tb_user();
        tb_user_info tbUserInfo = new tb_user_info();
        Pattern p = null;
        Matcher m = null;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        m = p.matcher(username);
        if (m.matches()) {
            tbUser = tbUserDao.findByPhoneAndPassword(username, password);
        } else {
            tbUser = tbUserDao.findByUsernameAndPassword(username, password);
        }
        if (tbUser == null) {
            throw new DataResultException("账号或密码错误");
        }
        if (tbUser.isActive() == false) {
            throw new DataResultException("账号未激活");
        }
        tbUserInfo = tbUserInfoDao.findByUserPk(tbUser.getPk());
        session.setAttribute("user", tbUser);
        session.setAttribute("user_info", tbUserInfo);
        dataResult.setMsg("登录成功");
        dataResult.setData(tbUserInfo);
        return dataResult;
    }

    @RequestMapping("register")
    @ResponseBody
    public DataResult registerIn(@RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String authority) {
        DataResult dataResult = new DataResult();
        if (tbUserDao.countByPhone(phone) != 0) {
            dataResult.setMsg("电话号码已经注册");
            dataResult.setCode(1);
            return dataResult;
        }
        if (tbUserDao.countByUsername(username) != 0) {
            dataResult.setMsg("用户已存在");
            dataResult.setCode(1);
            return dataResult;
        }
        tb_user tbUser = new tb_user();
        tbUser.setPhone(phone);
        tbUser.setUsername(username);
        tbUser.setPassword(password);
        tbUser.setActive(true);
        tbUser.setAuthority(authority);
        if (tbUserDao.save(tbUser) != null) {
            dataResult.setMsg("注册成功");
        }
        return dataResult;
    }

}
