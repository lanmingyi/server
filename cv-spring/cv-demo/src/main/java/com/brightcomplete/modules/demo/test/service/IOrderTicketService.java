package com.brightcomplete.modules.demo.test.service;

import java.util.List;

import com.brightcomplete.modules.demo.test.entity.OrderTicket;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单机票
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface IOrderTicketService extends IService<OrderTicket> {

    /**
     * 通过订单id查询订单机票
     * @param mainId 订单id
     * @return 订单机票集合
     */
	public List<OrderTicket> selectTicketsByMainId(String mainId);
}
