package com.brightcomplete.modules.system.controller;

import com.aliyun.oss.common.utils.StringUtils;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.system.base.controller.BaseController;
import com.brightcomplete.modules.system.entity.SysAppDetailed;
import com.brightcomplete.modules.system.service.SysAppDetailedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 移动端代码生成详情表
 *
 * @Author lmy
 * @Email
 * @Date 2022-12-05
 */

@RestController
@RequestMapping("/system/sysAppDetailed")
@Slf4j
@Api(tags="移动端代码")
public class SysAppDetailedController extends BaseController<SysAppDetailed,SysAppDetailedService> {
    @Autowired
    private SysAppDetailedService sysAppDetailedService;

    @ApiOperation(value = "更新SysAppDetailed模块数据")
//    @ApiImplicitParam(name = "sysAppDetailed", value = "更新SysAppDetailed模块数据", required = true, dataType = "SysAppDetailed")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@ModelAttribute SysAppDetailed sysAppDetailed) {
        if (StringUtils.isNullOrEmpty(sysAppDetailed.getFormData()) || StringUtils.isNullOrEmpty(sysAppDetailed.getSearchData()) || StringUtils.isNullOrEmpty(sysAppDetailed.getListData())) {
            Result.error("表单/列表数据不能为空！！！");
        }
        sysAppDetailedService.updateById(sysAppDetailed);
        return Result.ok("更新成功！");
    }

    //获取再次设计的数据
    @ResponseBody
    @PostMapping(value = "/getDesignData")
    public Object getDesignData(String uuid) {
        if (StringUtils.isNullOrEmpty(uuid)) {
            return Result.error("uuid不能为空!!!");
        }
        SysAppDetailed sysAppDetailed = sysAppDetailedService.getDetailByBasicsUuid(uuid);
        return sysAppDetailed;
    }
}
