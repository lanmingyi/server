package com.brightcomplete.modules.demo.test.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.brightcomplete.modules.demo.test.entity.OrderCustomer;
import com.brightcomplete.modules.demo.test.entity.OrderMain;
import com.brightcomplete.modules.demo.test.entity.OrderTicket;
import com.brightcomplete.modules.demo.test.mapper.OrderCustomerMapper;
import com.brightcomplete.modules.demo.test.mapper.OrderMainMapper;
import com.brightcomplete.modules.demo.test.mapper.OrderTicketMapper;
import com.brightcomplete.modules.demo.test.service.IOrderMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class OrderMainServiceImpl extends ServiceImpl<OrderMainMapper, OrderMain> implements IOrderMainService {

    @Autowired
    private OrderMainMapper orderMainMapper;
    @Autowired
    private OrderCustomerMapper orderCustomerMapper;
    @Autowired
    private OrderTicketMapper orderTicketMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList) {
        orderMainMapper.insert(orderMain);
        if (orderCustomerList != null) {
            for (OrderCustomer entity : orderCustomerList) {
                entity.setOrderId(orderMain.getId());
                orderCustomerMapper.insert(entity);
            }
        }
        if (orderTicketList != null) {
            for (OrderTicket entity : orderTicketList) {
                entity.setOrderId(orderMain.getId());
                orderTicketMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList) {
        orderMainMapper.updateById(orderMain);

        //1.先删除子表数据
        orderTicketMapper.deleteTicketsByMainId(orderMain.getId());
        orderCustomerMapper.deleteCustomersByMainId(orderMain.getId());

        //2.子表数据重新插入
        if (orderCustomerList != null) {
            for (OrderCustomer entity : orderCustomerList) {
                entity.setOrderId(orderMain.getId());
                orderCustomerMapper.insert(entity);
            }
        }
        if (orderTicketList != null) {
            for (OrderTicket entity : orderTicketList) {
                entity.setOrderId(orderMain.getId());
                orderTicketMapper.insert(entity);
            }
        }
    }

    /**
     * 一对多维护逻辑改造  LOWCOD-315
     * @param orderMain
     * @param orderCustomerList
     * @param orderTicketList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCopyMain(OrderMain orderMain, List<OrderCustomer> orderCustomerList, List<OrderTicket> orderTicketList) {
        orderMainMapper.updateById(orderMain);

        // 循环前台传过来的数据
        for (OrderTicket ticket: orderTicketList){
            // 先查询子表数据库
            OrderTicket orderTicket = orderTicketMapper.selectById(ticket.getId());
            if(orderTicket == null){
                // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
                ticket.setOrderId(orderMain.getId());
                orderTicketMapper.insert(ticket);
                break;
            }
            if(orderTicket.getId().equals(ticket.getId())){
                // 传过来的id和数据库id一至时，说明数据库存在该数据，走更新逻辑
                orderTicketMapper.updateById(ticket);
            }
        }
        for (OrderCustomer customer: orderCustomerList){
            // 先查询子表数据库
            OrderCustomer customers = orderCustomerMapper.selectById(customer.getId());
            if(customers == null){
                // 当传过来的id数据库不存在时，说明数据库没有，走新增逻辑
                customer.setOrderId(orderMain.getId());
                orderCustomerMapper.insert(customer);
                break;
            }
            if(customers.getId().equals(customer.getId())){
                //TODO 传过来的id和数据库id一至时，说明数据库存在该数据，走更新逻辑
                orderCustomerMapper.updateById(customer);
            }
        }
        // 当跟新和删除之后取差集， 当传过来的id不存在，而数据库存在时，说明已删除，走删除逻辑
        List<OrderTicket> orderTickets = orderTicketMapper.selectTicketsByMainId(orderMain.getId());
        List<OrderTicket> collect = orderTickets.stream()
                .filter(item -> !orderTicketList.stream()
                .map(e -> e.getId())
                .collect(Collectors.toList())
                .contains(item.getId()))
                .collect(Collectors.toList());
        // for循环删除id
        for (OrderTicket ticket:collect){
            orderTicketMapper.deleteById(ticket.getId());
        }

        List<OrderCustomer> orderCustomers = orderCustomerMapper.selectCustomersByMainId(orderMain.getId());
        List<OrderCustomer> customersCollect = orderCustomers.stream()
                .filter(item -> !orderCustomerList.stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toList())
                        .contains(item.getId()))
                .collect(Collectors.toList());
        //TODO for循环删除id
        for (OrderCustomer c:customersCollect){
            orderCustomerMapper.deleteById(c.getId());
        }
    }
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		orderMainMapper.deleteById(id);
		orderTicketMapper.deleteTicketsByMainId(id);
		orderCustomerMapper.deleteCustomersByMainId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orderMainMapper.deleteById(id);
			orderTicketMapper.deleteTicketsByMainId(id.toString());
			orderCustomerMapper.deleteCustomersByMainId(id.toString());
		}
	}

}
