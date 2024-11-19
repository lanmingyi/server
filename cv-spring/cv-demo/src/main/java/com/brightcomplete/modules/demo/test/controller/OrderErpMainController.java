package com.brightcomplete.modules.demo.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.system.query.QueryGenerator;
import com.brightcomplete.modules.demo.test.entity.OrderCustomer;
import com.brightcomplete.modules.demo.test.entity.OrderMain;
import com.brightcomplete.modules.demo.test.entity.OrderTicket;
import com.brightcomplete.modules.demo.test.service.IOrderCustomerService;
import com.brightcomplete.modules.demo.test.service.IOrderMainService;
import com.brightcomplete.modules.demo.test.service.IOrderTicketService;
import com.brightcomplete.modules.demo.test.vo.OrderMainPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description: 一对多示例（ERP TAB风格）
 * @Author: ZhiLin
 * @Date: 2019-02-20
 * @Version: v2.0
 */
@Slf4j
@RestController
@RequestMapping("/test/order")
public class OrderErpMainController {

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
    @GetMapping(value = "/orderList")
    public Result<?> respondePagedData(OrderMain orderMain,
                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
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
        orderMainService.save(orderMain);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param orderMainPage
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<?> edit(@RequestBody OrderMainPage orderMainPage) {
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderMainPage, orderMain);
        orderMainService.updateById(orderMain);
        return Result.ok("编辑成功!");
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
        this.orderMainService.removeByIds(Arrays.asList(ids.split(",")));
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
     * @param orderCustomer
     * @return
     */
    @GetMapping(value = "/listOrderCustomerByMainId")
    public Result<?> queryOrderCustomerListByMainId(OrderCustomer orderCustomer,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<OrderCustomer> queryWrapper = QueryGenerator.initQueryWrapper(orderCustomer, req.getParameterMap());
        Page<OrderCustomer> page = new Page<OrderCustomer>(pageNo, pageSize);
        IPage<OrderCustomer> pageList = orderCustomerService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 通过id查询
     *
     * @param orderTicket
     * @return
     */
    @GetMapping(value = "/listOrderTicketByMainId")
    public Result<?> queryOrderTicketListByMainId(OrderTicket orderTicket,
                                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                  HttpServletRequest req) {
        QueryWrapper<OrderTicket> queryWrapper = QueryGenerator.initQueryWrapper(orderTicket, req.getParameterMap());
        Page<OrderTicket> page = new Page<OrderTicket>(pageNo, pageSize);
        IPage<OrderTicket> pageList = orderTicketService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param orderCustomer
     * @return
     */
    @PostMapping(value = "/addCustomer")
    public Result<?> addCustomer(@RequestBody OrderCustomer orderCustomer) {
        orderCustomerService.save(orderCustomer);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param orderCustomer
     * @return
     */
    @RequestMapping(value = "/editCustomer", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<?> editCustomer(@RequestBody OrderCustomer orderCustomer) {
        orderCustomerService.updateById(orderCustomer);
        return Result.ok("添加成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteCustomer")
    public Result<?> deleteCustomer(@RequestParam(name = "id", required = true) String id) {
        orderCustomerService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchCustomer")
    public Result<?> deleteBatchCustomer(@RequestParam(name = "ids", required = true) String ids) {
        this.orderCustomerService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    /**
     * 添加
     *
     * @param orderTicket
     * @return
     */
    @PostMapping(value = "/addTicket")
    public Result<?> addTicket(@RequestBody OrderTicket orderTicket) {
        orderTicketService.save(orderTicket);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param orderTicket
     * @return
     */
    @RequestMapping(value = "/editTicket", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<?> editTicket(@RequestBody OrderTicket orderTicket) {
        orderTicketService.updateById(orderTicket);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteTicket")
    public Result<?> deleteTicket(@RequestParam(name = "id", required = true) String id) {
        orderTicketService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatchTicket")
    public Result<?> deleteBatchTicket(@RequestParam(name = "ids", required = true) String ids) {
        this.orderTicketService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

}