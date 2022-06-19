package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Orders;

public interface OrdersService {

	/**
	 * 根据客户名称查询客户订单
	 * 
	 */
	List<Orders> findAllOrdByName(String custName);
	
	/**
	 * 根据odrId查询客户订单
	 * 
	 */
	Orders findOrdersById(int odrId);
	
}
