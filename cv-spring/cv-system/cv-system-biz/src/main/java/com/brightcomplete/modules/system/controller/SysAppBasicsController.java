package com.brightcomplete.modules.system.controller;


import com.aliyun.oss.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.system.base.controller.BaseController;
import com.brightcomplete.modules.system.entity.SysAppBasics;
import com.brightcomplete.modules.system.service.SysAppBasicsService;
import com.brightcomplete.modules.system.vo.TaleDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移动端代码生成基础表
 *
 * @Author lmy
 * @Email
 * @Date 2022-12-03
 */
//@Api(tags = {"移动端代码生成基础表"})
@RestController
@RequestMapping("/system/sysAppBasics")
@Slf4j
public class SysAppBasicsController extends BaseController<SysAppBasics, SysAppBasicsService> {

    @Autowired
    private SysAppBasicsService sysAppBasicsService;

//    @ApiOperation(value = "获得SysAppClassification分页集数据")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, dataType = "Int"),
//            @ApiImplicitParam(name = "rows", value = "一页显示多少条记录", defaultValue = "20", required = true, dataType = "Int"),
//            @ApiImplicitParam(name = "sort", value = "排序", defaultValue = "createTime", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "order", value = "排序规则", defaultValue = "desc", required = true, dataType = "String"),
//    })


    @RequestMapping(value = "/getPageSet", method = RequestMethod.POST)
    public Object getPageSet( @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        String filterSort = "";
        // filterSort = BaseUtils.filterSort(request, filterSort + getAuthFilter());
        // PageSet<SysAppClassificationModel> pageSet = sysAppClassificationService.getPageSet(pageParam, filterSort);
        Page<SysAppBasics> page = new Page<SysAppBasics>(pageNo, pageSize);
        QueryWrapper<SysAppBasics> queryWrapper = new QueryWrapper<>();
        IPage<SysAppBasics> pageList = sysAppBasicsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    // 获取所有列
    @ResponseBody
    @RequestMapping(value = "/getColumnName", method = RequestMethod.POST)
    public Object getColumnName(String uuid) {
        if (StringUtils.isNullOrEmpty(uuid)) {
            return Result.error("uuid不能为空");
        }
        SysAppBasics sysAppBasics = sysAppBasicsService.getById(uuid);
        List<TaleDetailVo> list = sysAppBasicsService.getColumnName(sysAppBasics.getBasicsTableName(), sysAppBasics.getBasicsTableSchema());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setColumnNameColumnComment(list.get(i).getColumnName() + "(" + list.get(i).getColumnComment() + ")");
        }
        return list;
    }

}

