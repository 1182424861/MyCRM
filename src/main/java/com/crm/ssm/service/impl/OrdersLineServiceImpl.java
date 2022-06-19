package com.crm.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.ssm.entity.OrdersLine;
import com.crm.ssm.mapper.OrdersLineMapper;
import com.crm.ssm.service.OrdersLineService;

@Service
public class OrdersLineServiceImpl implements OrdersLineService{

	//�������ݲ�
	@Autowired
	private OrdersLineMapper ordersLineMapper;
	
	//����odrId��ѯOrdersLine
	@Override
	public List<OrdersLine> findOrdersLineByOdrId(int odrId) {
		return ordersLineMapper.findOdrLineAndProd(odrId);
	}

	
	
	
}
