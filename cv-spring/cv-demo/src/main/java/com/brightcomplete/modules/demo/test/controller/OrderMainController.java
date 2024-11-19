package com.brightcomplete.modules.demo.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.system.base.controller.BaseController;
import com.brightcomplete.common.system.query.QueryGenerator;
import com.brightcomplete.common.system.vo.LoginUser;
import com.brightcomplete.modules.demo.test.entity.OrderCustomer;
import com.brightcomplete.modules.demo.test.entity.OrderMain;
import com.brightcomplete.modules.demo.test.entity.OrderTicket;
import com.brightcomplete.modules.demo.test.service.IOrderCustomerService;
import com.brightcomplete.modules.demo.test.service.IOrderMainService;
import com.brightcomplete.modules.demo.test.service.IOrderTicketService;
import com.brightcomplete.modules.demo.test.vo.OrderMainPage;
import com.brightcomplete.poi.excel.ExcelImportUtil;
import com.brightcomplete.poi.excel.def.NormalExcelConstants;
import com.brightcomplete.poi.excel.entity.ExportParams;
import com.brightcomplete.poi.excel.entity.ImportParams;
import com.brightcomplete.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 一对多示例（BEditableTable行编辑）
 * @Author: lmy
 * @Date:2019-02-15
 * @Version: V2.0
 */
@RestController
@RequestMapping("/test/orderMain")
@Slf4j
public class OrderMainController extends BaseController<OrderMain, IOrderMainService> {

    @Autowired
    private IOrderMainService orderMainService;
    @Autowired
    private IOrderCustomerService orderCustomerService;
    @Autowired
    private IOrderTicketService orderTicketService;

    /**
     * 分页列表查询
     *
     * @param orderMain
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(OrderMain orderMain, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<OrderMain> queryWrapper = QueryGenerator.initQueryWrapper(orderMain, req.getParameterMap());
        Page<OrderMain> page = new Page<OrderMain>(pageNo, pageSize);
        IPage<OrderMain> pageList = orderMainService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param orderMainPage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody OrderMainPage orderMainPage) {
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderMainPage, orderMain);
        orderMainService.saveMain(orderMain, orderMainPage.getOrderCustomerList(), orderMainPage.getOrderTicketList());
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param orderMainPage
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<?> eidt(@RequestBody OrderMainPage orderMainPage) {
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderMainPage, orderMain);
        orderMainService.updateCopyMain(orderMain, orderMainPage.getOrderCustomerList(), orderMainPage.getOrderTicketList());
        return Result.ok("编辑成功！");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        orderMainService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.orderMainService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        OrderMain orderMain = orderMainService.getById(id);
        return Result.ok(orderMain);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOrderCustomerListByMainId")
    public Result<?> queryOrderCustomerListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<OrderCustomer> orderCustomerList = orderCustomerService.selectCustomersByMainId(id);
        return Result.ok(orderCustomerList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOrderTicketListByMainId")
    public Result<?> queryOrderTicketListByMainId(@RequestParam(name = "id", required = true) String id) {
        List<OrderTicket> orderTicketList = orderTicketService.selectTicketsByMainId(id);
        return Result.ok(orderTicketList);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OrderMain jeecgOrderMain) {
        // Step.1 组装查询条件
        QueryWrapper<OrderMain> queryWrapper = QueryGenerator.initQueryWrapper(jeecgOrderMain, request.getParameterMap());
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //获取当前用户
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        List<OrderMainPage> pageList = new ArrayList<OrderMainPage>();

        List<OrderMain> orderMainList = orderMainService.list(queryWrapper);
        for (OrderMain orderMain : orderMainList) {
            OrderMainPage vo = new OrderMainPage();
            BeanUtils.copyProperties(orderMain, vo);
            // 查询机票
            List<OrderTicket> orderTicketList = orderTicketService.selectTicketsByMainId(orderMain.getId());
            vo.setOrderTicketList(orderTicketList);
            // 查询客户
            List<OrderCustomer> orderCustomerList = orderCustomerService.selectCustomersByMainId(orderMain.getId());
            vo.setOrderCustomerList(orderCustomerList);
            pageList.add(vo);
        }

        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "一对多订单示例");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, OrderMainPage.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("自定义导出Excel内容标题", "导出人:" + sysUser.getRealname(), "自定义Sheet名字"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(2);
            params.setNeedSave(true);
            try {
                List<OrderMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), OrderMainPage.class, params);
                for (OrderMainPage page : list) {
                    OrderMain po = new OrderMain();
                    BeanUtils.copyProperties(page, po);
                    orderMainService.saveMain(po, page.getOrderCustomerList(), page.getOrderTicketList());
                }
                return Result.ok("文件导入成功！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败：" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("文件导入失败！");
    }

}
