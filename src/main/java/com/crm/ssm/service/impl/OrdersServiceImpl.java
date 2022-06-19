package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.Orders;
import com.crm.ssm.entity.OrdersExample;
import com.crm.ssm.mapper.OrdersMapper;
import com.crm.ssm.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

	//�������ݲ�
	@Autowired
	private OrdersMapper ordersMapper;
	
	//���ݿͻ����Ʋ�ѯ�ͻ�������Ϣ
	@Override
	public List<Orders> findAllOrdByName (String custName) {
		OrdersExample ordExample = new OrdersExample();
		ordExample.createCriteria().andOdrCustomerEqualTo(custName);
		return ordersMapper.selectByExample(ordExample);
	}

	//����odrId��ѯ�ͻ�����
	@Override
	public Orders findOrdersById(int odrId) {
		OrdersExample ordExample = new OrdersExample();
		ordExample.createCriteria().andOdrIdEqualTo(odrId);
		return ordersMapper.selectByExample(ordExample).get(0);
	}
	
}
