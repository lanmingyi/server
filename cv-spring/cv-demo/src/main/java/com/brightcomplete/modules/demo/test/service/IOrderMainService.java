package com.brightcomplete.modules.demo.test.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.brightcomplete.modules.demo.test.entity.OrderCustomer;
import com.brightcomplete.modules.demo.test.entity.OrderMain;
import com.brightcomplete.modules.demo.test.entity.OrderTicket;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface IOrderMainService extends IService<OrderMain> {

	/**
	 * 添加一对多
	 * @param orderMain 订单实体类
     * @param orderCustomerList 订单客户集合
     * @param orderTicketList 订单机票集合
	 */
	public void saveMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList) ;
	
	/**
	 * 修改一对多
     * @param orderMain 订单实体类
     * @param orderCustomerList 订单客户集合
     * @param orderTicketList 订单机票集合
	 */
	public void updateMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList);
	
	/**
	 * 删除一对多
	 * @param id 订单id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 * @param idList 订单id集合
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

    /**
     * 修改一对多
     * @param orderMain 订单实体类
     * @param orderCustomerList 订单客户集合
     * @param orderTicketList 订单机票集合
     */
	public void updateCopyMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList);
}
