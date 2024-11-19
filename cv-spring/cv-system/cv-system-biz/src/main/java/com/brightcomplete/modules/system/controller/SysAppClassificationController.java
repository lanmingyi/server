package com.brightcomplete.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.system.base.controller.BaseController;
import com.brightcomplete.modules.system.entity.SysAppClassification;
import com.brightcomplete.modules.system.service.SysAppClassificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 移动端代码生成分类表
 *
 * @Author lmy
 * @Email
 * @Date 2022-11-18
 */
//@Api(tags = {"移动端代码生成分类表"})
@RestController
@RequestMapping("/system/sysAppClassification")
@Slf4j
public class SysAppClassificationController extends BaseController<SysAppClassification, SysAppClassificationService> {

    @Autowired
    private SysAppClassificationService sysAppClassificationService;

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
//        filterSort = BaseUtils.filterSort(request, filterSort + getAuthFilter());
        //PageSet<SysAppClassificationModel> pageSet = sysAppClassificationService.getPageSet(pageParam, filterSort);
        Page<SysAppClassification> page = new Page<SysAppClassification>(pageNo, pageSize);
        QueryWrapper<SysAppClassification> queryWrapper = new QueryWrapper<>();
        IPage<SysAppClassification> pageList = sysAppClassificationService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

//    @ApiOperation(value = "获得SysAppCl" +
//            "assification模块详细数据")
//    @ApiImplicitParam(name = "uuid", value = "获得SysAppClassification模块详细数据", required = true, dataType = "String")
//    @ResponseBody
//    @RequestMapping(value = "/getDetailByUuid")
//    public Object getDetailByUuid(String uuid) {
//        SysAppClassificationModel sysAppClassificationModel = sysAppClassificationService.selectByPrimaryKey(uuid);
//        return sysAppClassificationModel;
//    }
//
//
//    @ApiOperation(value = "保存SysAppClassification模块数据")
//    @ApiImplicitParam(name = "sysAppClassification", value = "保存SysAppClassification模块数据", required = true, dataType = "SysAppClassification")
//    @ResponseBody
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public Object save(@ModelAttribute SysAppClassificationModel sysAppClassificationModel) {
//        int result = sysAppClassificationService.insertSelective(getSaveData(sysAppClassificationModel));
//        return result > 0 ? success("保存成功！") : failure("保存失败！");
//    }
//
//
//    @ApiOperation(value = "更新SysAppClassification模块数据")
//    @ApiImplicitParam(name = "sysAppClassification", value = "更新SysAppClassification模块数据", required = true, dataType = "SysAppClassification")
//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Object update(@ModelAttribute SysAppClassificationModel sysAppClassificationModel) {
//        int result = sysAppClassificationService.updateByPrimaryKeySelective(getUpdateData(sysAppClassificationModel));
//        return result > 0 ? success("更新成功！") : failure("更新失败！");
//    }
//
//    @ApiOperation(value = "删除SysAppClassification模块数据")
//    @ApiImplicitParam(name = "sysAppClassification", value = "删除SysAppClassification模块数据", required = true, dataType = "SysAppClassification")
//    @ResponseBody
//    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
//    public Object deleteBatch(@RequestParam String[] uuid) {
//        int result = sysAppClassificationService.executeDeleteBatch(uuid);
//        return result > 0 ? success("删除成功！") : failure("删除失败！");
//    }

}

