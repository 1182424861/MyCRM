package com.crm.ssm.service;

import java.util.List;

import com.crm.ssm.entity.Orders;

public interface OrdersService {

	/**
	 * ���ݿͻ����Ʋ�ѯ�ͻ�����
	 * 
	 */
	List<Orders> findAllOrdByName(String custName);
	
	/**
	 * ����odrId��ѯ�ͻ�����
	 * 
	 */
	Orders findOrdersById(int odrId);
	
}
