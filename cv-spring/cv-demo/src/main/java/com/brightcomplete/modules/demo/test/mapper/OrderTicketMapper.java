package com.brightcomplete.modules.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import com.brightcomplete.modules.demo.test.entity.OrderTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单机票
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface OrderTicketMapper extends BaseMapper<OrderTicket> {

	/**
	 *  通过主表外键批量删除客户
	 * @param mainId
	 * @return
	 */
    @Delete("DELETE FROM ORDER_TICKET WHERE ORDER_ID = #{mainId}")
	public boolean deleteTicketsByMainId(String mainId);

    /**
     * 通过主表订单外键查询订单机票
     * @param mainId 订单id
     * @return 返回订单机票集合
     */
    @Select("SELECT * FROM ORDER_TICKET WHERE ORDER_ID = #{mainId}")
	public List<OrderTicket> selectTicketsByMainId(String mainId);
}
