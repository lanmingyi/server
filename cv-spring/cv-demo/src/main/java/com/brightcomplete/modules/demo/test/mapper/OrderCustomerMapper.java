package com.brightcomplete.modules.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import com.brightcomplete.modules.demo.test.entity.OrderCustomer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单客户
 * @Author: lmy
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface OrderCustomerMapper extends BaseMapper<OrderCustomer> {
	
	/**
	 *  通过主表外键批量删除客户
	 * @param mainId
	 * @return
	 */
    @Delete("DELETE FROM ORDER_CUSTOMER WHERE ORDER_ID = #{mainId}")
	public boolean deleteCustomersByMainId(String mainId);

    /**
     * 通过主表订单外键查询客户
     * @param mainId 订单id
     * @return 订单客户集合
     */
    @Select("SELECT * FROM ORDER_CUSTOMER WHERE ORDER_ID = #{mainId}")
	public List<OrderCustomer> selectCustomersByMainId(String mainId);
}
