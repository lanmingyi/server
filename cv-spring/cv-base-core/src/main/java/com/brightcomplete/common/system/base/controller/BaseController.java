package com.brightcomplete.common.system.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.util.oConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import com.brightcomplete.common.system.query.QueryGenerator;
import com.brightcomplete.common.system.vo.LoginUser;
import com.brightcomplete.poi.excel.ExcelImportUtil;
import com.brightcomplete.poi.excel.def.NormalExcelConstants;
import com.brightcomplete.poi.excel.entity.ExportParams;
import com.brightcomplete.poi.excel.entity.ImportParams;
import com.brightcomplete.poi.excel.entity.enmus.ExcelType;
import com.brightcomplete.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Description: Controller基类
 * @Author: lmy
 * @Date: 2023-03-16
 * @Version: 1.0
 */
@Slf4j
public class BaseController<T, S extends IService<T>> {
    /**
     * BaseController注入service时改用protected修饰，能避免重复引用service
     */
    @Autowired
    protected S service;

    @Value("${brightcomplete.path.upload}")
    private String upLoadPath;

    /**
     * 导出excel
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title) {
        // Step.1 组装查询条件
        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        // Step.2 获取导出数据
        List<T> exportList = service.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.FILE_NAME, title);
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        //update-begin for：图片导出报错，ImageBasePath未设置
        ExportParams exportParams = new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title);
        exportParams.setImageBasePath(upLoadPath);
        //update-end for：图片导出报错，ImageBasePath未设置
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 根据每页sheet数量导出多sheet
     *
     * @param request
     * @param object       实体类
     * @param clazz        实体类class
     * @param title        标题
     * @param exportFields 导出字段自定义
     * @param pageNum      每个sheet的数据条数
     * @param request
     */
    protected ModelAndView exportXlsSheet(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields, Integer pageNum) {
        // Step.1 组装查询条件
        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        // Step.2 计算分页sheet数据
        double total = service.count();
        int count = (int) Math.ceil(total / pageNum);
        //update-begin for: 多sheet导出根据选择导出写法调整
        // Step.3  过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //update-end for: 多sheet导出根据选择导出写法调整
        // Step.4 多sheet处理
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= count; i++) {
            Page<T> page = new Page<T>(i, pageNum);
            IPage<T> pageList = service.page(page, queryWrapper);
            List<T> exportList = pageList.getRecords();
            Map<String, Object> map = new HashMap<>(5);
            ExportParams exportParams = new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title + i, upLoadPath);
            exportParams.setType(ExcelType.XSSF);
            //map.put("title",exportParams);
            //表格Title
            map.put(NormalExcelConstants.PARAMS, exportParams);
            //表格对应实体
            map.put(NormalExcelConstants.CLASS, clazz);
            //数据集合
            map.put(NormalExcelConstants.DATA_LIST, exportList);
            listMap.add(map);
        }
        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.FILE_NAME, title);
        mv.addObject(NormalExcelConstants.MAP_LIST, listMap);
        return mv;
    }


    /**
     * 根据权限导出excel，传入导出字段参数
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields) {
        ModelAndView mv = this.exportXls(request, object, clazz, title);
        mv.addObject(NormalExcelConstants.EXPORT_FIELDS, exportFields);
        return mv;
    }

    /**
     * 获取对象ID
     *
     * @return
     */
    private String getId(T item) {
        try {
            return PropertyUtils.getProperty(item, "id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    protected Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
                //update-begin for:批量插入数据
                long start = System.currentTimeMillis();
                service.saveBatch(list);
                //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
                //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
                log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
                //update-end for:批量插入数据
                return Result.ok("文件导入成功！数据行数：" + list.size());
            } catch (Exception e) {
                //update-begin for: 导入数据重复增加提示
                String msg = e.getMessage();
                log.error(msg, e);
                if (msg != null && msg.indexOf("Duplicate entry") >= 0) {
                    return Result.error("文件导入失败:有重复数据！");
                } else {
                    return Result.error("文件导入失败:" + e.getMessage());
                }
                //update-end for: 导入数据重复增加提示
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("文件导入失败！");
    }
}
