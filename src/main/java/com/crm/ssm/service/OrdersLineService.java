package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.OrdersLine;

public interface OrdersLineService {

	/**
	 * ����odrId��ѯOrdersLine
	 * 
	 */
	List<OrdersLine> findOrdersLineByOdrId(int odrId);
	
}
