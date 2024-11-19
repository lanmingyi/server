package com.brightcomplete.modules.demo.test.service;

import java.util.List;

import com.brightcomplete.modules.demo.test.entity.OrderCustomer;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单客户
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface IOrderCustomerService extends IService<OrderCustomer> {

    /**
     * 根据订单id获取订单客户数据
     * @param mainId 订单id
     * @return 订单顾客集合
     */
	public List<OrderCustomer> selectCustomersByMainId(String mainId);
}
