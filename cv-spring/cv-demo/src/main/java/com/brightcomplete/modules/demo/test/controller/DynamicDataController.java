package com.brightcomplete.modules.demo.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.aspect.annotation.AutoLog;
import com.brightcomplete.common.system.base.controller.BaseController;
import com.brightcomplete.modules.demo.test.entity.Demo;
import com.brightcomplete.modules.demo.test.service.IDemoService;
import com.brightcomplete.modules.demo.test.service.IDynamicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 动态数据源测试
 * @author lmy
 * @Date:2020-04-21
 */
@Slf4j
@Api(tags = "动态数据源测试")
@RestController
@RequestMapping("/test/dynamic")
public class DynamicDataController extends BaseController<Demo, IDemoService> {

    @Autowired
    private IDynamicDataService dynamicDataService;


    /**
     * 动态切换数据源

     * @return
     */
    @PostMapping(value = "/test1")
    @AutoLog(value = "动态切换数据源")
    @ApiOperation(value = "动态切换数据源", notes = "动态切换数据源")
    public Result<List<Demo>> selectSpelByKey(@RequestParam(required = false) String dsName) {
        List<Demo> list = dynamicDataService.selectSpelByKey(dsName);
        return Result.OK(list);
    }


}
