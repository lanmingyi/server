package com.brightcomplete.modules.demo.test.service.impl;

import java.util.List;

import com.brightcomplete.modules.demo.test.entity.OrderTicket;
import com.brightcomplete.modules.demo.test.mapper.OrderTicketMapper;
import com.brightcomplete.modules.demo.test.service.IOrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单机票
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class OrderTicketServiceImpl extends ServiceImpl<OrderTicketMapper, OrderTicket> implements IOrderTicketService {
	@Autowired
	private OrderTicketMapper orderTicketMapper;
	
	@Override
	public List<OrderTicket> selectTicketsByMainId(String mainId) {
		return orderTicketMapper.selectTicketsByMainId(mainId);
	}

}
