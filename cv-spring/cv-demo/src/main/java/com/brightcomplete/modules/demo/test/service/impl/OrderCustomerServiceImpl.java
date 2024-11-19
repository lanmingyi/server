package com.brightcomplete.modules.demo.test.service.impl;

import java.util.List;

import com.brightcomplete.modules.demo.test.entity.OrderCustomer;
import com.brightcomplete.modules.demo.test.mapper.OrderCustomerMapper;
import com.brightcomplete.modules.demo.test.service.IOrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单客户
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class OrderCustomerServiceImpl extends ServiceImpl<OrderCustomerMapper, OrderCustomer> implements IOrderCustomerService {

	@Autowired
	private OrderCustomerMapper orderCustomerMapper;
	
	@Override
	public List<OrderCustomer> selectCustomersByMainId(String mainId) {
		return orderCustomerMapper.selectCustomersByMainId(mainId);
	}

}
