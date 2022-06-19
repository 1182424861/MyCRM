package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.OrdersLine;

public interface OrdersLineService {

	/**
	 * ¸ù¾ÝodrId²éÑ¯OrdersLine
	 * 
	 */
	List<OrdersLine> findOrdersLineByOdrId(int odrId);
	
}
