package com.sun.gisvisualition.controller.in;

import com.sun.gisvisualition.dao.tb_remoteDao;
import com.sun.gisvisualition.entity.DataResult;
import com.sun.gisvisualition.entity.tb_remote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


@Controller
@RequestMapping("corpEvolution")
public class CropEvolutionController {

    @Autowired
    private tb_remoteDao tbRemoteDao;

    @RequestMapping("getByPk")
    @ResponseBody
    public DataResult getByPk(String pk) {
        DataResult dataResult = new DataResult();

        Optional<tb_remote> remote = tbRemoteDao.findById(pk);
        if (remote == null) {
            dataResult.setMsg("未查询到数据");
            dataResult.setCount(0L);
        } else {
            dataResult.setData(remote);
            dataResult.setCount(1L);
            dataResult.setMsg("查询成功");
        }
        return dataResult;
    }
}
